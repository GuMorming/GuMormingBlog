package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.entity.User;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.UploadService;
import cn.edu.whut.gumorming.service.UserService;
import cn.edu.whut.gumorming.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/8 13:29
 * @Email : gumorming@163.com
 * @Description :
 */
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;
    @Autowired
    private UserService userService;
    
    @PostMapping("/blog/upload")
    public ResponseResult<String> uploadAvatar(@RequestParam("img") MultipartFile img) {
        try {
            // 上传后获取图片url
            String url = uploadService.uploadImg(img);
            // 存入数据库
            User user = userService.getById(SecurityUtils.getUserId());
            user.setAvatar(url);
            userService.updateById(user);
            // 返回url
            return ResponseResult.okResult(url);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("图片上传失败");
        }
    }
}