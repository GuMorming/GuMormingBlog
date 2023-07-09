package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 用户表(User)表服务接口
 *
 * @author gumorming
 * @since 2023-07-07 13:49:37
 */
public interface UserService extends IService<User> {
    
    ResponseResult getUserInfo();
    
    ResponseResult updateUserInfo(User user);
    
    ResponseResult register(User user);
    
}