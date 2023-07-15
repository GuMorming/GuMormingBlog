package cn.edu.whut.gumorming.model.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户登录信息")
public class UserInfoVO {
    
    /**
     * 用户id
     */
    @Schema(title = "用户id")
    private Long id;
    
    /**
     * 用户头像
     */
    @Schema(title = "用户头像")
    private String avatar;
    
    /**
     * 用户昵称
     */
    @Schema(title = "用户昵称")
    private String nickname;
    
    /**
     * 用户名
     */
    @Schema(title = "用户名")
    private String username;
    
    /**
     * 用户邮箱
     */
    @Schema(title = "用户邮箱")
    private String email;
    
    /**
     * 个人网站
     */
    @Schema(title = "个人网站")
    private String webSite;
    
    /**
     * 个人简介
     */
    @Schema(title = "个人简介")
    private String intro;
    
    /**
     * 点赞文章集合
     */
    @Schema(title = "点赞文章集合")
    private Set<Object> articleLikeSet;
    
    /**
     * 点赞评论集合
     */
    @Schema(title = "点赞评论集合")
    private Set<Object> commentLikeSet;
    
    /**
     * 点赞说说集合
     */
    @Schema(title = "点赞说说集合")
    private Set<Object> talkLikeSet;
    
    /**
     * 登录类型
     */
    @Schema(title = "登录类型")
    private Integer loginType;
}