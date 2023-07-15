package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.CommentConstants;
import cn.edu.whut.gumorming.entity.Comment;
import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import cn.edu.whut.gumorming.mapper.CommentMapper;
import cn.edu.whut.gumorming.model.dto.comment.AddCommentDTO;
import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.vo.comment.CommentVO;
import cn.edu.whut.gumorming.model.vo.reply.ReplyVO;
import cn.edu.whut.gumorming.model.vo.response.PageVo;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.CommentService;
import cn.edu.whut.gumorming.service.SiteConfigService;
import cn.edu.whut.gumorming.service.UserService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import cn.edu.whut.gumorming.utils.HTMLUtils;
import cn.edu.whut.gumorming.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author gumorming
 * @since 2023-07-07 19:44:02
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private UserService userService;
    @Autowired
    private SiteConfigService siteConfigService;
    
    /**
     * 博客获取评论列表
     */
    @Override
    public ResponseResult<PageVo> commentList(GetParamsDTO getParamsDTO) {
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 1.查询条件
        commentLambdaQueryWrapper
                // 评论类型
                .eq(Objects.nonNull(getParamsDTO.getCommentType()), Comment::getCommentType, getParamsDTO.getCommentType())
                // 评论对应Id
                .eq(Objects.nonNull(getParamsDTO.getTypeId()), Comment::getTypeId, getParamsDTO.getTypeId())
                // 根评论
                .eq(Comment::getParentId, CommentConstants.ROOT)
                .orderByAsc(Comment::getCreateTime);
        // 2.分页
        Page<Comment> page = new Page<>(getParamsDTO.getCurrent(), getParamsDTO.getSize());
        page(page, commentLambdaQueryWrapper);
        // 获取带回复的根评论集合
        List<CommentVO> commentVoList = toCommentVOList(page.getRecords());
        
        return ResponseResult.okResult(new PageVo<CommentVO>(commentVoList, page.getTotal()));
    }
    
    @Override
    public ResponseResult addComment(AddCommentDTO addCommentDTO) {
        // 评论内容不能为空
        if (!StringUtils.hasText(addCommentDTO.getCommentContent())) {
            return ResponseResult.errorResult(HttpCodeEnum.COMMENT_CONTENT_NOT_NULL);
        }//... todo 校验评论
        // 查看网站配置信息,是否需要检查评论
        Integer isCommentCheck = siteConfigService.getSiteConfig().getCommentCheck();
        // 获取 comment对象
        Comment comment = BeanCopyUtils.copyBean(addCommentDTO, Comment.class);
        // 填充评论字段
        comment.setFromUid(SecurityUtils.getUserId());
        if (Objects.isNull(addCommentDTO.getParentId())) {
            comment.setParentId(CommentConstants.ROOT);
        }
        comment.setCommentContent(HTMLUtils.filter(comment.getCommentContent()));
        comment.setIsCheck(isCommentCheck.equals(CommentConstants.IS_CHECK_FALSE) ? CommentConstants.IS_CHECK_TRUE : CommentConstants.IS_CHECK_FALSE);
        // 存入数据库中
        save(comment);
        
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult<PageVo<ReplyVO>> getPageReplyVOList(Long commentId, GetParamsDTO getParamsDTO) {
        // 获取以此 commentId 为 parentId 的所有评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId, commentId)
                .orderByAsc(Comment::getCreateTime);
        // 封装VO
        Page<Comment> page = new Page<>(getParamsDTO.getCurrent(), getParamsDTO.getSize());
        page(page, queryWrapper);
        List<ReplyVO> replyVOs = getReplyVOList(page.getRecords());
        
        return ResponseResult.okResult(new PageVo<>(replyVOs, (long) replyVOs.size()));
    }
    
    
    private List<CommentVO> toCommentVOList(List<Comment> comments) {
        // 获取每个评论的回复,并存入Map中
        Map<Long, List<ReplyVO>> replyVOListMap = new HashMap<>();
        for (Comment comment : comments) {
            List<Comment> replyList = list(
                    new LambdaQueryWrapper<Comment>()
                            .eq(Comment::getParentId, comment.getId())
                            .orderByAsc(Comment::getCreateTime)
            );
            List<ReplyVO> replyVOS = getReplyVOList(replyList);
            replyVOListMap.put(comment.getId(), replyVOS);
        }
        
        List<CommentVO> commentVos = BeanCopyUtils.copyBeanList(comments, CommentVO.class);
        // 遍历VO集合
        for (CommentVO commentVo : commentVos) {
            // 通过FromUid查询用户昵称并赋值
            String nickName = userService.getById(commentVo.getFromUid()).getNickname();
            commentVo.setFromNickname(nickName);
            // 设置头像
            String avatar = userService.getById(commentVo.getFromUid()).getAvatar();
            commentVo.setAvatar(avatar);
            // todo likecount
            // 回复集合
            commentVo.setReplyVOList(replyVOListMap.get(commentVo.getId()));
            // 回复数
            commentVo.setReplyCount(replyVOListMap.get(commentVo.getId()).size());
        }
        
        
        return commentVos;
    }
    
    private List<ReplyVO> getReplyVOList(List<Comment> replyList) {
        List<ReplyVO> replyVOS = BeanCopyUtils.copyBeanList(replyList, ReplyVO.class);
        for (ReplyVO replyVO : replyVOS) {
            // 通过FromUid查询用户昵称并赋值
            String nickName = userService.getById(replyVO.getFromUid()).getNickname();
            replyVO.setFromNickname(nickName);
            // 设置头像
            String avatar = userService.getById(replyVO.getFromUid()).getAvatar();
            replyVO.setAvatar(avatar);
            // 通过 toUid查询用户昵称
            String toNickName = userService.getById(replyVO.getToUid()).getNickname();
            replyVO.setToNickname(toNickName);
        }
        return replyVOS;
    }
}