package cn.edu.whut.gumorming.model.vo.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "评论后台VO")
public class CommentBackVO {
    
    /**
     * 评论id
     */
    @Schema(title = "评论id")
    private String id;
    
    /**
     * 头像
     */
    @Schema(title = "头像")
    private String avatar;
    
    /**
     * 评论用户昵称
     */
    @Schema(title = "评论用户昵称")
    private String fromNickname;
    
    /**
     * 被回复用户昵称
     */
    @Schema(title = "被回复用户昵称")
    private String toNickname;
    
    /**
     * 文章标题
     */
    @Schema(title = "文章标题")
    private String articleTitle;
    
    /**
     * 评论内容
     */
    @Schema(title = "评论内容")
    private String commentContent;
    
    /**
     * 评论类型
     */
    @Schema(title = "评论类型")
    private Integer commentType;
    
    /**
     * 是否通过 (0否 1是)
     */
    @Schema(title = "是否通过 (0否 1是)")
    private Integer isCheck;
    
    /**
     * 发表时间
     */
    @Schema(title = "发表时间")
    private LocalDateTime createTime;
    
}