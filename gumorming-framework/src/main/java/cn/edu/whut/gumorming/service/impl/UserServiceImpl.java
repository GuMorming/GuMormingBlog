package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.entity.User;
import cn.edu.whut.gumorming.domain.enums.AppHttpCodeEnum;
import cn.edu.whut.gumorming.domain.vo.UserInfoVo;
import cn.edu.whut.gumorming.exception.SystemException;
import cn.edu.whut.gumorming.mapper.UserMapper;
import cn.edu.whut.gumorming.service.UserService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import cn.edu.whut.gumorming.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用户表(User)表服务实现类
 *
 * @author gumorming
 * @since 2023-07-07 13:49:38
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public ResponseResult getUserInfo() {
        // 获取当前用户id
        Long userId = SecurityUtils.getUserId();
        // 根据用户id 查询用户信息
        User user = getById(userId);
        //封装VO
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        
        return ResponseResult.okResult(userInfoVo);
    }
    
    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        // TODO 用update()方法指定更新的列
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult register(User user) {
        // 对数据进行非空判断 null ""
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.REGISTER_USERNAME_NULL);
        } else if (!StringUtils.hasText(user.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.REGISTER_PASSWORD_NULL);
        } else if (!StringUtils.hasText(user.getNickName())) {
            throw new SystemException(AppHttpCodeEnum.REGISTER_NICKNAME_NULL);
        } else if (!StringUtils.hasText(user.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.REGISTER_EMAIL_NULL);
        }
        // 对数据进行重复性判断
        if (usernameExist(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        } else if (emailExist(user.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }//...
        
        // 密码加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        // 存入数据库
        save(user);
        
        return ResponseResult.okResult();
    }
    
    
    private boolean usernameExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName);
        return count(queryWrapper) > 0;
    }
    
    private boolean emailExist(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, email);
        return count(queryWrapper) > 0;
    }
}