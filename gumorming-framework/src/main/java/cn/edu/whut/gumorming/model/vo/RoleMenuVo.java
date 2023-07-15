package cn.edu.whut.gumorming.model.vo;

import cn.edu.whut.gumorming.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.domain.vo
 * @createTime : 2023/7/11 10:30
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuVo {
    // 全部菜单树
    List<Menu> menus;
    // 角色所关联的权限
    List<Long> checkedKeys;
}