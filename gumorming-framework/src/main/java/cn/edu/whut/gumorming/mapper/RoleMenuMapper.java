package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色和菜单关联表(RoleMenu)表数据库访问层
 *
 * @author gumorming
 * @since 2023-07-11 09:59:03
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

}