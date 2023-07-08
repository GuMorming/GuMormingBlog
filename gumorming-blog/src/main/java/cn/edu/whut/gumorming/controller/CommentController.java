package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.constants.SystemConstants;
import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.entity.Comment;
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
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    
    @GetMapping("/commentList")
    public ResponseResult commentList(Integer pageNum, Integer pageSize, Long articleId) {
        return commentService.commentList(SystemConstants.COMMENT_TYPE_ARTICLE, pageNum, pageSize, articleId);
    }
    
    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.COMMENT_TYPE_LINK, pageNum, pageSize, null);
    }
    
    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }
}