package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.SystemConstants;
import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.entity.Comment;
import cn.edu.whut.gumorming.domain.enums.AppHttpCodeEnum;
import cn.edu.whut.gumorming.domain.vo.CommentVo;
import cn.edu.whut.gumorming.domain.vo.PageVo;
import cn.edu.whut.gumorming.exception.SystemException;
import cn.edu.whut.gumorming.mapper.CommentMapper;
import cn.edu.whut.gumorming.service.CommentService;
import cn.edu.whut.gumorming.service.UserService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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
    
    
    @Override
    public ResponseResult commentList(Integer pageNum, Integer pageSize, Long articleId) {
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 1.查询对应文章的根评论
        // 对articleId进行判断
        commentLambdaQueryWrapper.eq(Comment::getArticleId, articleId);
        // 根评论 rootId == -1
        commentLambdaQueryWrapper.eq(Comment::getRootId, SystemConstants.COMMENT_ROOT);
        // 2.分页
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, commentLambdaQueryWrapper);
        
        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());
        
        // 3. 查询所有根评论的子评论,并赋值给对应的属性
        for (CommentVo commentVo : commentVoList) {
            // 查询对应的子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            // 赋值
            commentVo.setChildren(children);
        }
        
        return ResponseResult.okResult(new PageVo(commentVoList, page.getTotal()));
    }
    
    @Override
    public ResponseResult addComment(Comment comment) {
        // 评论内容不能为空
        if (!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.COMMENT_CONTENT_NOT_NULL);
        }
        // 存入数据库中
        save(comment);
        
        return ResponseResult.okResult();
    }
    
    /**
     * 根据根评论id查询对应子评论的集合
     *
     * @param id 根评论id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        
        List<Comment> comments = list(queryWrapper);
        
        return toCommentVoList(comments);
    }
    
    private List<CommentVo> toCommentVoList(List<Comment> list) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        // 遍历VO集合
        for (CommentVo commentVo : commentVos) {
            // 通过CreateBy查询用户昵称并赋值
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);
            // 通过toCommentUserId查询用户昵称并赋值
            // 若toCommentUserId不为 -1(根评论) 才去查询
            if (commentVo.getToCommentUserId() != SystemConstants.COMMENT_ROOT_USER) {
                String toCommentUsername = userService.getById(commentVo.getToCommentUserId()).getUserName();
                commentVo.setToCommentUserName(toCommentUsername);
            }
        }
        
        
        return commentVos;
    }
}