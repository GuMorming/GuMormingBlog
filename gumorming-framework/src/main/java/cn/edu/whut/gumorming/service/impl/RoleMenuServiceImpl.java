package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.entity.RoleMenu;
import cn.edu.whut.gumorming.mapper.RoleMenuMapper;
import cn.edu.whut.gumorming.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表(RoleMenu)表服务实现类
 *
 * @author gumorming
 * @since 2023-07-11 09:59:03
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}