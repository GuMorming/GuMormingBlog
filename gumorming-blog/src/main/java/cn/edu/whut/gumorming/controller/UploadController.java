package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.annotation.SystemLog;
import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    
    @PostMapping("/upload")
    @SystemLog("上传头像")
    public ResponseResult uploadAvatar(MultipartFile img) {
        return uploadService.uploadImg(img);
    }
}