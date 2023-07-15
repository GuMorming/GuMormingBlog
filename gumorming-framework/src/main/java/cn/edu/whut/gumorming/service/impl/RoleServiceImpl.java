package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.SystemConstants;
import cn.edu.whut.gumorming.entity.Role;
import cn.edu.whut.gumorming.entity.RoleMenu;
import cn.edu.whut.gumorming.entity.UserRole;
import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import cn.edu.whut.gumorming.mapper.RoleMapper;
import cn.edu.whut.gumorming.model.dto.role.AddRoleDto;
import cn.edu.whut.gumorming.model.dto.role.QueryRoleDto;
import cn.edu.whut.gumorming.model.vo.response.PageVo;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.RoleMenuService;
import cn.edu.whut.gumorming.service.RoleService;
import cn.edu.whut.gumorming.service.UserRoleService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import cn.edu.whut.gumorming.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author gumorming
 * @since 2023-07-09 19:10:44
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    RoleMenuService roleMenuService;
    @Autowired
    UserRoleService userRoleService;
    
    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        // 管理员, 返回集合中只需要有admin
        if (SecurityUtils.isAdmin()) {
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        // 否则,查询用户对应角色信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }
    
    @Override
    public ResponseResult<PageVo> listPageRole(Integer pageNum, Integer pageSize, QueryRoleDto queryRoleDto) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        // 查询条件
        queryWrapper.eq(Objects.nonNull(queryRoleDto.getIsDisable()), Role::getIsDisable, queryRoleDto.getIsDisable())
                .like(StringUtils.hasText(queryRoleDto.getRoleName()), Role::getRoleName, queryRoleDto.getRoleName())
                .orderByAsc(Role::getRoleSort);
        // 分页
        Page<Role> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        // 封装VO
        PageVo<Role> pageVo = new PageVo<>(page.getRecords(), page.getTotal());
        // 返回结果
        return ResponseResult.okResult(pageVo);
    }
    
    @Override
    public ResponseResult changeStatus(QueryRoleDto queryRoleDto) {
        // 根据id获取role对象
        Role role = getById(queryRoleDto.getRoleId());
        // 更新条件
        LambdaUpdateWrapper<Role> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Role::getIsDisable, queryRoleDto.getIsDisable())
                .eq(Role::getId, queryRoleDto.getRoleId());
        update(role, updateWrapper);
        // 返回response
        return ResponseResult.okResult();
    }
    
    @Override
    @Transactional
    public ResponseResult addRole(AddRoleDto addRoleDto) {
        // 添加 角色
        Role role = BeanCopyUtils.copyBean(addRoleDto, Role.class);
        save(role);
        // 添加 角色和菜单权限 的关联
        List<RoleMenu> roleMenus = addRoleDto.getMenuIds().stream()
                .map(menuId -> new RoleMenu(role.getId(), menuId))
                .toList();
        roleMenuService.saveBatch(roleMenus);
        
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult getRoleById(Long id) {
        Role role = getById(id);
        return ResponseResult.okResult(role);
    }
    
    @Override
    @Transactional
    public ResponseResult updateRoleById(AddRoleDto addRoleDto) {
        // 更新 角色信息
        Role role = BeanCopyUtils.copyBean(addRoleDto, Role.class);
        updateById(role);
        // 清除 角色和权限 关联
        LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenu::getRoleId, role.getId());
        roleMenuService.remove(queryWrapper);
        // 添加 角色和权限 的关联
        List<RoleMenu> roleMenus = addRoleDto.getMenuIds().stream()
                .map(menuId -> new RoleMenu(role.getId(), menuId))
                .toList();
        roleMenuService.saveBatch(roleMenus);
        
        return ResponseResult.okResult();
    }
    
    @Override
    @Transactional
    public ResponseResult deleteRole(List<Long> ids) {
        if (ids.contains(1L)) {
            return ResponseResult.errorResult(HttpCodeEnum.DELETE_ADMIN_DENY);
        }
        for (Long roleId : ids) {
            removeById(roleId);
            // 删除与该角色关联的 userRole
            userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getRoleId, roleId));
        }
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult listAllNormalStatusRole() {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        // 状态正常的角色
        queryWrapper.eq(Role::getIsDisable, SystemConstants.FALSE);
        List<Role> roles = list(queryWrapper);
        // TODO 封装VO
        
        return ResponseResult.okResult(roles);
    }
}