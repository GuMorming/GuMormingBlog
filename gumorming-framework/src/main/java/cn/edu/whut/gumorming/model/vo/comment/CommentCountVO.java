package cn.edu.whut.gumorming.model.vo.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 评论数量VO
 **/
@Data
@Schema(description = "评论数量VO")
public class CommentCountVO {
    /**
     * 类型id
     */
    @Schema(title = "类型id")
    private Integer id;
    
    /**
     * 评论数量
     */
    @Schema(title = "评论数量")
    private Integer commentCount;
}