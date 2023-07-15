package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.dto.user.AdminAddUserDTO;
import cn.edu.whut.gumorming.model.dto.user.AdminUpdateUserDTO;
import cn.edu.whut.gumorming.model.vo.response.PageVo;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/11 10:56
 * @Email : gumorming@163.com
 * @Description :
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/list")
    public ResponseResult<PageVo> getPageUserList(GetParamsDTO getParamsDTO) {
        return userService.getPageUserList(getParamsDTO);
    }
    
    @GetMapping("/{id}")
    public ResponseResult getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
    @PostMapping
    public ResponseResult addUser(@RequestBody AdminAddUserDTO adminAddUserDTO) {
        return userService.addUser(adminAddUserDTO);
    }
    
    @PutMapping
    public ResponseResult updateUser(@RequestBody AdminUpdateUserDTO updateUserDTO) {
        return userService.updateUserById(updateUserDTO);
    }
    
    @PutMapping("/changeStatus")
    public ResponseResult changeUserStatus(@RequestBody AdminUpdateUserDTO adminUpdateUserDTO) {
        return userService.updateUserStatusById(adminUpdateUserDTO);
    }
    
    @DeleteMapping("/{id}")
    public ResponseResult deleteUser(@PathVariable List<Long> id) {
        return userService.deleteUser(id);
    }
}