package cn.edu.whut.gumorming.model.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog-Back
 * @Package : cn.edu.whut.gumorming.model.dto
 * @createTime : 2023/7/14 8:39
 * @Email : gumorming@163.com
 * @Description :
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
//@GroupSequenceProvider(value = CommentProvider.class)
@Schema(description = "评论DTO")
public class AddCommentDTO {
    
    /**
     * 类型id
     */
//    @NotNull(message = "类型id不能为空", groups = {ArticleTalk.class})
//    @Null(message = "类型id必须为空", groups = {Link.class})
    @Schema(title = "类型id")
    private Long typeId;
    
    /**
     * 评论类型 (1文章 2友链 3说说)
     */
//    @CommentType(values = {1, 2, 3}, message = "评论类型只能为1、2、3")
    @NotNull(message = "评论类型不能为空")
    @Schema(title = "评论类型 (1文章 2友链 3说说)")
    private Integer commentType;
    
    /**
     * 父评论id
     */
//    @Null(groups = {ParentIdNull.class})
//    @NotNull(groups = {ParentIdNotNull.class})
    @Schema(title = "父评论id")
    private Long parentId;
    
    /**
     * 被回复评论id
     */
//    @Null(message = "reply_id、to_uid必须都为空", groups = {ParentIdNull.class})
//    @NotNull(message = "回复评论id和回复用户id不能为空", groups = {ParentIdNotNull.class})
    @Schema(title = "被回复评论id")
    private Long replyId;
    
    /**
     * 被回复用户id
     */
//    @Null(message = "reply_id、to_uid必须都为空", groups = {ParentIdNull.class})
//    @NotNull(message = "回复评论id和回复用户id不能为空", groups = {ParentIdNotNull.class})
    @Schema(title = "被回复用户id")
    private Long toUid;
    
    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空")
    @Schema(title = "评论内容")
    private String commentContent;
    
}