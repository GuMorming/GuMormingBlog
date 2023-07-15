package cn.edu.whut.gumorming.model.vo.comment;

import cn.edu.whut.gumorming.model.vo.reply.ReplyVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 评论VO
 **/
@Data
@Schema(description = "评论VO")
public class CommentVO {
    
    /**
     * 评论id
     */
    @Schema(title = "评论id")
    private Long id;
    
    /**
     * 评论用户id
     */
    @Schema(title = "评论用户id")
    private Long fromUid;
    
    /**
     * 昵称
     */
    @Schema(title = "昵称")
    private String fromNickname;
    
    /**
     * 个人网站
     */
    @Schema(title = "个人网站")
    private String webSite;
    
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
     * 回复量
     */
    @Schema(title = "回复量")
    private Integer replyCount;
    
    /**
     * 回复列表
     */
    @Schema(title = "回复列表")
    private List<ReplyVO> replyVOList;
    
    /**
     * 评论时间
     */
    @Schema(title = "评论时间")
    private Date createTime;
}