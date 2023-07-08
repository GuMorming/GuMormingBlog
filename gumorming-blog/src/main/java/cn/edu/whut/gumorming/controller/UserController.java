package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.annotation.SystemLog;
import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.entity.User;
import cn.edu.whut.gumorming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/8 10:57
 * @Email : gumorming@163.com
 * @Description :
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/userInfo")
    @SystemLog("获取用户信息")
    public ResponseResult getUserInfo() {
        return userService.getUserInfo();
    }
    
    @PutMapping("/userInfo")
    @SystemLog("更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }
    
    @PostMapping("/register")
    @SystemLog("用户注册")
    public ResponseResult register(@RequestBody User user) {
        return userService.register(user);
        
    }
}