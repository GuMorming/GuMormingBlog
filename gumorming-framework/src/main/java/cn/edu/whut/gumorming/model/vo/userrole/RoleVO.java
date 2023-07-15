package cn.edu.whut.gumorming.model.vo.userrole;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色VO
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户角色VO")
public class RoleVO {
    
    /**
     * 角色id
     */
    @Schema(title = "角色id")
    private Long id;
    
    /**
     * 角色名
     */
    @Schema(title = "角色名")
    private String roleName;
}