package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.domain.entity.User;
import cn.edu.whut.gumorming.mapper.UserMapper;
import cn.edu.whut.gumorming.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author gumorming
 * @since 2023-07-07 13:49:38
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}