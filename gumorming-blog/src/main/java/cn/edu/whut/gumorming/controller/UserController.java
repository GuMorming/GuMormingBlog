package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.annotation.SystemLog;
import cn.edu.whut.gumorming.entity.User;
import cn.edu.whut.gumorming.model.dto.UserInfoDTO;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
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
@RequestMapping("/blog/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/userInfo")
    @SystemLog("博客获取用户信息")
    public ResponseResult getUserInfo() {
        return userService.getUserInfo();
    }
    
    @PutMapping("/userInfo")
    @SystemLog("博客更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody UserInfoDTO userInfoDTO) {
        return userService.updateUserInfo(userInfoDTO);
    }
    
    @PostMapping("/register")
    @SystemLog("博客用户注册")
    public ResponseResult register(@RequestBody User user) {
        return userService.register(user);
        
    }
}