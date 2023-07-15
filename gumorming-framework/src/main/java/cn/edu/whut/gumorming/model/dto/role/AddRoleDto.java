package cn.edu.whut.gumorming.model.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.domain.dto
 * @createTime : 2023/7/11 9:56
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRoleDto {
    //角色ID@TableId
    private Long id;
    //角色名称
    private String roleName;
    //角色权限字符串
    private String roleKey;
    //显示顺序
    private Integer roleSort;
    //角色状态（0正常 1停用）
    private String status;
    //备注
    private String remark;
    // 权限
    List<Long> menuIds;
}