package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.annotation.SystemLog;
import cn.edu.whut.gumorming.entity.LoginUser;
import cn.edu.whut.gumorming.entity.Menu;
import cn.edu.whut.gumorming.entity.User;
import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import cn.edu.whut.gumorming.exception.SystemException;
import cn.edu.whut.gumorming.model.vo.AdminUserInfoVo;
import cn.edu.whut.gumorming.model.vo.RouterVo;
import cn.edu.whut.gumorming.model.vo.UserInfoVo;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.AdminLoginService;
import cn.edu.whut.gumorming.service.MenuService;
import cn.edu.whut.gumorming.service.RoleService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import cn.edu.whut.gumorming.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/9 16:14
 * @Email : gumorming@163.com
 * @Description :
 */
@RestController
public class AdminLoginController {
    @Autowired
    private AdminLoginService adminLoginService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    
    
    @PostMapping("/user/login")
    @SystemLog("后台登录")
    public ResponseResult adminLogin(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUsername())) {
            // 异常 必须要传用户名
            throw new SystemException(HttpCodeEnum.REQUIRE_USERNAME);
        }
        return adminLoginService.login(user);
    }
    
    @GetMapping("/getInfo")
    @SystemLog("后台用户信息")
    public ResponseResult<AdminUserInfoVo> getInfo() {
        // 获取当前登录用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        // 根据用户ID 查询权限信息
        List<String> perms = menuService.selectByUserId(loginUser.getUser().getId());
        // 根据用户id 查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
        // 获取用户信息
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        // 封装VO
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms, roleKeyList, userInfoVo);
        
        return ResponseResult.okResult(adminUserInfoVo);
    }
    
    @GetMapping("/getRouters")
    @SystemLog("后台路由信息")
    public ResponseResult<RouterVo> getRouters() {
        Long userId = SecurityUtils.getUserId();
        // 查询menu 结果为json tree
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        // 封装VO
        RouterVo routerVo = new RouterVo(menus);
        
        return ResponseResult.okResult(routerVo);
    }
    
    @PostMapping("/user/logout")
    @SystemLog("后台注销")
    public ResponseResult logout() {
        return adminLoginService.logout();
    }
}