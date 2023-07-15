package cn.edu.whut.gumorming.model.vo.reply;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 回复数VO
 */
@Data
@Schema(description = "回复数VO")
public class ReplyCountVO {
    
    /**
     * 评论id
     */
    @Schema(title = "评论id")
    private Integer commentId;
    
    /**
     * 回复数
     */
    @Schema(title = "回复数")
    private Integer replyCount;
}