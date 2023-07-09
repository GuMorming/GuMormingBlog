package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.SystemConstants;
import cn.edu.whut.gumorming.domain.entity.Menu;
import cn.edu.whut.gumorming.mapper.MenuMapper;
import cn.edu.whut.gumorming.service.MenuService;
import cn.edu.whut.gumorming.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author gumorming
 * @since 2023-07-09 19:04:49
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    
    @Override
    public List<String> selectByUserId(Long id) {
        // 管理员,返回所有权限
        if (SecurityUtils.isAdmin()) {
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType, SystemConstants.MENU_TYPE_CAIDAN, SystemConstants.MENU_TYPE_BUTTON);
            wrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(wrapper);
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }
        // 否则返回相应权限
        return getBaseMapper().selectPermsByUserId(id);
    }
    
    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;
        // 管理员, 返回所有正常状态Menu
        if (SecurityUtils.isAdmin()) {
            menus = menuMapper.selectAllRouterMenu();
        } else {
            // 返回相应权限Menu
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }
        // 构建menu tree
        List<Menu> menuTree = buildMenuTree(menus, 0L);
        
        return menuTree;
    }
    
    private List<Menu> buildMenuTree(List<Menu> menus, long parentId) {
        // 先找出最高层的菜单,然后找出其子菜单,设置到其children属性中
        return menus
                .stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChilden(menus, menu)))
                .collect(Collectors.toList());
    }
    
    /**
     * 获取传入menu的子menu
     *
     * @param menus menu集合
     * @param menu  需要获得children的menu
     * @return
     */
    private List<Menu> getChilden(List<Menu> menus, Menu menu) {
        return menus
                .stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                // 递归, 再次设置children
                .map(m -> m.setChildren(getChilden(menus, m)))
                .collect(Collectors.toList());
    }
}