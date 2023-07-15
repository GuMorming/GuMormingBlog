package cn.edu.whut.gumorming.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 用户表(User)表实体类
 *
 * @author gumorming
 * @since 2023-07-07 13:49:36
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("user")
public class User {
    //主键@TableId
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 用户昵称
     */
    private String nickname;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 用户头像
     */
    private String avatar;
    
    /**
     * 个人网站
     */
    private String webSite;
    
    /**
     * 个人介绍
     */
    private String intro;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 登录ip
     */
    private String ipAddress;
    
    /**
     * 登录地址
     */
    private String ipSource;
    
    /**
     * 登录方式 (1邮箱 2QQ 3Gitee 4Github)
     */
    private Integer loginType;
    
    /**
     * 是否禁用 (0否 1是)
     */
    private Integer isDisable;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 登录时间
     */
    private Date loginTime;
    
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    @TableLogic
    private Integer isDelete;
    
    
}