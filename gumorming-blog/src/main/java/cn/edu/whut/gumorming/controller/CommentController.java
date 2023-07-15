package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.annotation.SystemLog;
import cn.edu.whut.gumorming.model.dto.comment.AddCommentDTO;
import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.vo.reply.ReplyVO;
import cn.edu.whut.gumorming.model.vo.response.PageVo;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/7 20:29
 * @Email : gumorming@163.com
 * @Description :
 */
@RestController
@RequestMapping("/blog/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    
    @GetMapping("/list")
    public ResponseResult<PageVo> getCommentList(GetParamsDTO getParamsDTO) {
        return commentService.commentList(getParamsDTO);
    }
    
    @GetMapping("/{commentId}/reply")
    public ResponseResult<PageVo<ReplyVO>> getCommentReplyVOList(@PathVariable Long commentId, GetParamsDTO getParamsDTO) {
        return commentService.getPageReplyVOList(commentId, getParamsDTO);
    }
    
    
    @PostMapping
    @SystemLog("发表评论")
    public ResponseResult addComment(@RequestBody AddCommentDTO addCommentDTO) {
        return commentService.addComment(addCommentDTO);
    }
    
    
}