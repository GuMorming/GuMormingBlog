package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.Comment;
import cn.edu.whut.gumorming.model.dto.comment.AddCommentDTO;
import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.vo.PageVo;
import cn.edu.whut.gumorming.model.vo.reply.ReplyVO;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 评论表(Comment)表服务接口
 *
 * @author gumorming
 * @since 2023-07-07 19:44:01
 */
public interface CommentService extends IService<Comment> {
    
    ResponseResult<PageVo> commentList(GetParamsDTO getParamsDTO);
    
    ResponseResult addComment(AddCommentDTO comment);
    
    ResponseResult<PageVo<ReplyVO>> getPageReplyVOList(Long commentId, GetParamsDTO getParamsDTO);
}