package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.entity.User;
import cn.edu.whut.gumorming.domain.enums.AppHttpCodeEnum;
import cn.edu.whut.gumorming.exception.SystemException;
import cn.edu.whut.gumorming.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/7 14:19
 * @Email : gumorming@163.com
 * @Description :
 */
@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;
    
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
            // 异常 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }
    
    @PostMapping("/logout")
    public ResponseResult logout() {
        return blogLoginService.logout();
    }
}