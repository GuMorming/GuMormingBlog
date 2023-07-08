package cn.edu.whut.gumorming.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.domain.vo
 * @createTime : 2023/7/7 20:38
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {
    @TableId
    private Long id;
    
    //文章id
    private Long articleId;
    //根评论id
    private Long rootId;
    //评论内容
    private String content;
    //所回复的目标评论的userid
    private Long toCommentUserId;
    // 所回复的目标评论的username
    private String toCommentUserName;
    //回复目标评论id
    private Long toCommentId;
    // 评论人userId
    private Long createBy;
    // 评论人username
    private String username;
    private Date createTime;
    
    private List<CommentVo> children;
    
}