package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.entity.User;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.service
 * @createTime : 2023/7/9 16:19
 * @Email : gumorming@163.com
 * @Description :
 */

public interface AdminLoginService {
    ResponseResult login(User user);
    
    ResponseResult logout();
}