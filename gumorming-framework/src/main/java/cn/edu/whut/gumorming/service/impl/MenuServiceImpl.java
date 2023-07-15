package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.SystemConstants;
import cn.edu.whut.gumorming.entity.Menu;
import cn.edu.whut.gumorming.entity.RoleMenu;
import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import cn.edu.whut.gumorming.mapper.MenuMapper;
import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.model.vo.rolemenu.RoleMenuVo;
import cn.edu.whut.gumorming.service.MenuService;
import cn.edu.whut.gumorming.service.RoleMenuService;
import cn.edu.whut.gumorming.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author gumorming
 * @since 2023-07-09 19:04:49
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    RoleMenuService roleMenuService;
    
    @Override
    public List<String> selectByUserId(Long id) {
        // 管理员,返回所有权限
        if (SecurityUtils.isAdmin()) {
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType, SystemConstants.MENU_TYPE_CAIDAN, SystemConstants.MENU_TYPE_BUTTON);
            wrapper.eq(Menu::getIsDisable, SystemConstants.FALSE);
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
    
    @Override
    public ResponseResult listAllMenu(GetParamsDTO getParamsDTO) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        // 查询条件
        queryWrapper.eq(Objects.nonNull(getParamsDTO.getIsDisable()), Menu::getIsDisable, getParamsDTO.getIsDisable());
        queryWrapper.like(StringUtils.hasText(getParamsDTO.getMenuName()), Menu::getMenuName, getParamsDTO.getMenuName());
        // TODO 封装VO
        List<Menu> menus = list(queryWrapper);
        
        return ResponseResult.okResult(menus);
    }
    
    @Override
    public ResponseResult addMenu(Menu menu) {
        save(menu);
        
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult getMenuById(Long id) {
        Menu menu = getById(id);
        
        return ResponseResult.okResult(menu);
    }
    
    @Override
    public ResponseResult updateMenu(Menu menu) {
        if (menu.getParentId().equals(menu.getId())) {
            return ResponseResult.errorResult(HttpCodeEnum.MENU_PARENT_SELF);
        }
        updateById(menu);
        
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult deleteMenuByid(Long menuId) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        // 查询是否存在子菜单
        queryWrapper.eq(Menu::getParentId, menuId);
        List<Menu> childrens = list(queryWrapper);
        if (!childrens.isEmpty()) {
            return ResponseResult.errorResult(HttpCodeEnum.MENU_CHILDREN_EXIST);
        }
        // 设置逻辑删除位
        LambdaUpdateWrapper<Menu> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(Menu::getIsDelete, Boolean.TRUE)
                .eq(Menu::getId, menuId);
        update(updateWrapper);
        // 返回结果
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult treeselect() {
        MenuMapper menuMapper = getBaseMapper();
        // 返回所有正常状态Menu
        List<Menu> menus = menuMapper.selectAllRouterMenu();
        // 构建menu tree
        List<Menu> menuTree = buildMenuTree(menus, 0L);
        
        return ResponseResult.okResult(menuTree);
    }
    
    @Override
    public ResponseResult roleMenuTreeselect(Long id) {
        LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        MenuMapper menuMapper = getBaseMapper();
        // 获取所有菜单
        List<Menu> menus = menuMapper.selectAllRouterMenu();
        // 构建menuTree
        List<Menu> menuTree = buildMenuTree(menus, 0L);
        // 获取角色对应 menuKey
        List<Long> checkedKeys = null;
        // 管理员返回所有权限
        if (id.equals(1L)) {
            checkedKeys = list().stream()
                    .map(Menu::getId)
                    .toList();
        } else {
            queryWrapper.eq(RoleMenu::getRoleId, id);
            checkedKeys = roleMenuService.list(queryWrapper).stream()
                    .map(RoleMenu::getMenuId)
                    .toList();
        }
        // 封装VO
        RoleMenuVo roleMenuVo = new RoleMenuVo(menuTree, checkedKeys);
        
        return ResponseResult.okResult(roleMenuVo);
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