package cn.edu.whut.gumorming.model.vo.user;

import cn.edu.whut.gumorming.model.vo.userrole.RoleVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 用户后台VO
 **/
@Data
@Schema(description = "用户后台VO")
public class AdminUserVO {
    
    /**
     * 用户id
     */
    @Schema(title = "用户id")
    private Long id;
    
    @Schema(title = "用户名")
    private String username;
    /**
     * 用户昵称
     */
    @Schema(title = "用户昵称")
    private String nickname;
    
    
    /**
     * 用户角色
     */
    @Schema(title = "用户角色")
    private List<RoleVO> roleList;
    
    /**
     * 是否禁用 (0否 1是)
     */
    @Schema(title = "是否禁用 (0否 1是)")
    private Integer isDisable;
    
    /**
     * 登录时间
     */
    @Schema(title = "登录时间")
    private Date loginTime;
    
    /**
     * 创建时间
     */
    @Schema(title = "创建时间")
    private Date createTime;
    
}