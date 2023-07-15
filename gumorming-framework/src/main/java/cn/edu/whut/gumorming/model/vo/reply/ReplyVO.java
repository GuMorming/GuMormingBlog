package cn.edu.whut.gumorming.model.vo.reply;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 回复VO
 **/
@Data
@Schema(description = "回复VO")
public class ReplyVO {
    
    /**
     * 评论id
     */
    @Schema(title = "评论id")
    private Long id;
    
    /**
     * 父级评论id
     */
    @Schema(title = "父级评论id")
    private Long parentId;
    
    /**
     * 评论用户id
     */
    @Schema(title = "评论用户id")
    private Long fromUid;
    
    /**
     * 被评论用户id
     */
    @Schema(title = "被评论用户id")
    private Long toUid;
    
    /**
     * 评论用户昵称
     */
    @Schema(title = "评论用户昵称")
    private String fromNickname;
    
    /**
     * 个人网站
     */
    @Schema(title = "个人网站")
    private String webSite;
    
    /**
     * 被评论用户昵称
     */
    @Schema(title = "被评论用户昵称")
    private String toNickname;
    
    /**
     * 头像
     */
    @Schema(title = "头像")
    private String avatar;
    
    /**
     * 评论内容
     */
    @Schema(title = "评论内容")
    private String commentContent;
    
    /**
     * 点赞数
     */
    @Schema(title = "点赞数")
    private Integer likeCount;
    
    /**
     * 评论时间
     */
    @Schema(title = "评论时间")
    private Date createTime;
}