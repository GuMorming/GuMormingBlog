package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.User;
import cn.edu.whut.gumorming.model.dto.RegisterDTO;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.service
 * @createTime : 2023/7/7 14:20
 * @Email : gumorming@163.com
 * @Description :
 */

public interface BlogLoginService {
    ResponseResult login(User user);
    
    ResponseResult logout();
    
    ResponseResult<?> sendCode(String username);
    
    ResponseResult register(RegisterDTO registerDTO);
}