package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.annotation.SystemLog;
import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.entity.User;
import cn.edu.whut.gumorming.domain.enums.AppHttpCodeEnum;
import cn.edu.whut.gumorming.exception.SystemException;
import cn.edu.whut.gumorming.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    
    @PostMapping("/user/login")
    @SystemLog("后台登录")
    public ResponseResult adminLogin(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
            // 异常 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return adminLoginService.login(user);
    }
    
    @PostMapping("/user/logout")
    @SystemLog("后台注销登录")
    public ResponseResult logout() {
        return adminLoginService.logout();
    }
}