package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.SystemConstants;
import cn.edu.whut.gumorming.entity.Role;
import cn.edu.whut.gumorming.entity.User;
import cn.edu.whut.gumorming.entity.UserRole;
import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import cn.edu.whut.gumorming.exception.SystemException;
import cn.edu.whut.gumorming.mapper.UserMapper;
import cn.edu.whut.gumorming.model.dto.UserInfoDTO;
import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.dto.user.AdminAddUserDTO;
import cn.edu.whut.gumorming.model.dto.user.AdminUpdateUserDTO;
import cn.edu.whut.gumorming.model.vo.PageVo;
import cn.edu.whut.gumorming.model.vo.QueryUserVo;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.model.vo.user.AdminUserVO;
import cn.edu.whut.gumorming.model.vo.user.UserInfoVO;
import cn.edu.whut.gumorming.model.vo.userrole.RoleVO;
import cn.edu.whut.gumorming.service.RoleService;
import cn.edu.whut.gumorming.service.UserRoleService;
import cn.edu.whut.gumorming.service.UserService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import cn.edu.whut.gumorming.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 用户表(User)表服务实现类
 *
 * @author gumorming
 * @since 2023-07-07 13:49:38
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    
    @Override
    public ResponseResult getUserInfo() {
        // 获取当前用户id
        Long userId = SecurityUtils.getUserId();
        // 根据用户id 查询用户信息
        User user = getById(userId);
        //封装VO
        UserInfoVO userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVO.class);
        
        return ResponseResult.okResult(userInfoVo);
    }
    
    @Override
    public ResponseResult updateUserInfo(UserInfoDTO userInfoDTO) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        // 更新当前登录用户
        User user = BeanCopyUtils.copyBean(userInfoDTO, User.class);
        user.setId(SecurityUtils.getUserId());
        updateById(user);
        
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult register(User user) {
        // 校验用户信息的存在性和重复性
        checkUserRegister(user);
        if (isPasswordNull(user.getPassword())) {
            return ResponseResult.errorResult(HttpCodeEnum.REGISTER_PASSWORD_NULL);
        }
        // 密码加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        // 存入数据库
        save(user);
        
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult<PageVo> getPageUserList(GetParamsDTO getParamsDTO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        // 查询条件
        queryWrapper
                // 是否禁用
                .eq(Objects.nonNull(getParamsDTO.getIsDisable()), User::getIsDisable, getParamsDTO.getIsDisable())
                // 用户名
                .like(StringUtils.hasText(getParamsDTO.getUsername()), User::getUsername, getParamsDTO.getUsername())
                .eq(User::getIsDelete, SystemConstants.FALSE);
        // 分页
        Page<User> page = new Page<>(getParamsDTO.getCurrent(), getParamsDTO.getSize());
        page(page, queryWrapper);
        // 获取用户角色
        Map<Long, List<RoleVO>> userRoleVOMap = new HashMap<>();
        List<User> users = page.getRecords();
        for (User user : users) {
            List<UserRole> userRoles = userRoleService.list(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getId()));
            for (UserRole userRole : userRoles) {
                List<Role> roles = roleService.list(new LambdaQueryWrapper<Role>().eq(Role::getId, userRole.getRoleId()));
                List<RoleVO> roleVOS = BeanCopyUtils.copyBeanList(roles, RoleVO.class);
                userRoleVOMap.put(user.getId(), roleVOS);
            }
        }
        // 封装Vo
        List<AdminUserVO> adminUserVOS = BeanCopyUtils.copyBeanList(page.getRecords(), AdminUserVO.class);
        for (AdminUserVO adminUserVO : adminUserVOS) {
            adminUserVO.setRoleList(userRoleVOMap.get(adminUserVO.getId()));
        }
        // 设置
        PageVo<AdminUserVO> vo = new PageVo<>(adminUserVOS, page.getTotal());
        
        return ResponseResult.okResult(vo);
    }
    
    @Override
    @Transactional
    public ResponseResult addUser(AdminAddUserDTO adminAddUserDTO) {
        // 获取用户对象
        User user = BeanCopyUtils.copyBean(adminAddUserDTO, User.class);
        // 校验用户信息的存在性和重复性
        checkUserRegister(user);
        if (isPasswordNull(user.getPassword())) {
            return ResponseResult.errorResult(HttpCodeEnum.REGISTER_PASSWORD_NULL);
        }
        // 密码加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        // 存入数据库
        save(user);
        // 保存 用户和角色 关联信息
        List<UserRole> userRoles = adminAddUserDTO.getRoleIds().stream()
                .map(roleId -> new UserRole(user.getId(), roleId))
                .toList();
        userRoleService.saveBatch(userRoles);
        
        return ResponseResult.okResult();
    }
    
    @Override
    @Transactional
    public ResponseResult deleteUser(List<Long> id) {
        // 不允许删除 管理员
        if (id.contains(1L)) {
            return ResponseResult.errorResult(HttpCodeEnum.DELETE_ADMIN_DENY);
        }
        for (Long userId : id) {
            removeById(userId);
            // 删除用户与角色的关联
            userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, userId));
        }
        return ResponseResult.okResult();
    }
    
    /**
     * 返回对应id的User信息, 该User对应关联的角色id列表 及 所有角色列表
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult getUserById(Long id) {
        // 1.获取id对应User信息
        User user = getById(id);
        // 2.获取该User关联的角色id
        LambdaQueryWrapper<UserRole> userRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userRoleLambdaQueryWrapper.eq(UserRole::getUserId, id);
        List<Long> roleIds = userRoleService.list(userRoleLambdaQueryWrapper).stream()
                .map(UserRole::getRoleId)
                .toList();
        // 3.所有角色列表(正常状态)
        LambdaQueryWrapper<Role> roleQueryWrapper = new LambdaQueryWrapper<>();
        roleQueryWrapper.eq(Role::getIsDisable, SystemConstants.FALSE);
        List<RoleVO> roles = BeanCopyUtils.copyBeanList(roleService.list(roleQueryWrapper), RoleVO.class);
        // 4.封装VO
        QueryUserVo queryUserVo = new QueryUserVo(user, roleIds, roles);
        
        return ResponseResult.okResult(queryUserVo);
    }
    
    @Override
    @Transactional
    public ResponseResult updateUserById(AdminUpdateUserDTO updateUserDTO) {
        // 1.获取User对象
        User user = BeanCopyUtils.copyBean(updateUserDTO, User.class);
        // 2.校验User信息并存入数据库
//        checkUserRegister(user);
        updateById(user);
        // 3.获取关联角色id并创建UserRole对象
        List<Long> roleIds = updateUserDTO.getRoleIds();
        List<UserRole> userRoles = roleIds.stream()
                .map(roleId -> new UserRole(user.getId(), roleId))
                .toList();
        // 4.清除user关联的角色信息
        LambdaUpdateWrapper<UserRole> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(UserRole::getUserId, user.getId());
        userRoleService.remove(updateWrapper);
        // 5.user role 关联关系保存
        userRoleService.saveBatch(userRoles);
        
        return ResponseResult.okResult();
        
    }
    
    @Override
    public ResponseResult updateUserStatusById(AdminUpdateUserDTO adminUpdateUserDTO) {
        User user = BeanCopyUtils.copyBean(adminUpdateUserDTO, User.class);
        
        update(user, new LambdaUpdateWrapper<User>()
                .eq(User::getId, user.getId())
                .set(User::getIsDisable, user.getIsDisable())
        );
        
        return ResponseResult.okResult();
    }
    
    /**
     * 校验用户信息的存在性和重复性
     *
     * @param user
     */
    private void checkUserRegister(User user) {
        // 对数据进行非空判断 null ""
        if (!StringUtils.hasText(user.getUsername())) {
            throw new SystemException(HttpCodeEnum.REGISTER_USERNAME_NULL);
        }
//        else if (!StringUtils.hasText(user.getPassword())) {
//            throw new SystemException(HttpCodeEnum.REGISTER_PASSWORD_NULL);
//        }
        else if (!StringUtils.hasText(user.getNickname())) {
            throw new SystemException(HttpCodeEnum.REGISTER_NICKNAME_NULL);
        } else if (!StringUtils.hasText(user.getEmail())) {
            throw new SystemException(HttpCodeEnum.REGISTER_EMAIL_NULL);
        }
        // 对数据进行重复性判断
        if (usernameExist(user.getUsername())) {
            throw new SystemException(HttpCodeEnum.USERNAME_EXIST);
        } else if (emailExist(user.getEmail())) {
            throw new SystemException(HttpCodeEnum.EMAIL_EXIST);
        }//...
    }
    
    private boolean isPasswordNull(String password) {
        if (!StringUtils.hasText(password)) {
            return true;
        }
        return false;
    }
    
    private boolean usernameExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userName);
        return count(queryWrapper) > 0;
    }
    
    private boolean emailExist(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, email);
        return count(queryWrapper) > 0;
    }
}