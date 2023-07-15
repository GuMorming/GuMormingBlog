package cn.edu.whut.gumorming.model.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
@Schema(description = "后台登录用户信息")
public class UserBackInfoVO {
    
    /**
     * 用户id
     */
    @Schema(title = "用户id")
    private Long id;
    
    /**
     * 头像
     */
    @Schema(title = "头像")
    private String avatar;
    
    /**
     * 角色
     */
    @Schema(title = "角色")
    private List<String> roleList;
    
    /**
     * 权限标识
     */
    @Schema(title = "权限标识")
    private List<String> permissionList;
    
}