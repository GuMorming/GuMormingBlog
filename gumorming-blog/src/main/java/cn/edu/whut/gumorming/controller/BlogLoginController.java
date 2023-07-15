package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.annotation.SystemLog;
import cn.edu.whut.gumorming.entity.User;
import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import cn.edu.whut.gumorming.exception.SystemException;
import cn.edu.whut.gumorming.model.dto.RegisterDTO;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.BlogLoginService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
    @SystemLog("博客登录")
    public ResponseResult login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUsername())) {
            // 异常 必须要传用户名
            throw new SystemException(HttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }
    
    /**
     * 发送邮箱验证码
     *
     * @return {@link ResponseResult<>}
     */
    
    @Operation(summary = "发送邮箱验证码")
    @GetMapping("/code")
    public ResponseResult<?> sendCode(String username) {
        return blogLoginService.sendCode(username);
    }
    
    @PostMapping("/register")
    public ResponseResult register(@RequestBody RegisterDTO register) {
        return blogLoginService.register(register);
    }
    
    @PostMapping("/logout")
    @SystemLog("博客注销登录")
    public ResponseResult logout() {
        return blogLoginService.logout();
    }
}