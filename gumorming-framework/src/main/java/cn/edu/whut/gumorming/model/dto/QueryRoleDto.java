package cn.edu.whut.gumorming.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.domain.dto
 * @createTime : 2023/7/11 9:14
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryRoleDto {
    // id
    private Long roleId;
    // 角色名称
    private String roleName;
    // 状态
    private Integer isDisable;
}