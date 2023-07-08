package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.service
 * @createTime : 2023/7/8 13:30
 * @Email : gumorming@163.com
 * @Description :
 */

public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}