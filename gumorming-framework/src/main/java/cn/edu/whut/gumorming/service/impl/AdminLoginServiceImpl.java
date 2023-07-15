package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.RedisConstants;
import cn.edu.whut.gumorming.entity.LoginUser;
import cn.edu.whut.gumorming.entity.User;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.model.vo.user.AdminLoginUserVo;
import cn.edu.whut.gumorming.service.AdminLoginService;
import cn.edu.whut.gumorming.service.UserService;
import cn.edu.whut.gumorming.utils.JwtUtil;
import cn.edu.whut.gumorming.utils.RedisCache;
import cn.edu.whut.gumorming.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.service.impl
 * @createTime : 2023/7/9 16:20
 * @Email : gumorming@163.com
 * @Description :
 */
@Service("adminLoginService")
public class AdminLoginServiceImpl implements AdminLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserService userService;
    
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);// 调用自定义 UserDetailsService
        // 判断是否认证通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 获取userid生产token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        // 用户信息存入redis
        redisCache.setCacheObject(RedisConstants.ADMIN_LOGIN_PREFIX + userId, loginUser);
        // 登录时间更新
        user.setLoginTime(new Date());
        userService.update(new LambdaUpdateWrapper<User>()
                .set(User::getLoginTime, new Date())
                .eq(User::getId, Long.parseLong(userId)));
        // token封装,返回
        AdminLoginUserVo vo = new AdminLoginUserVo(jwt);
        
        return ResponseResult.okResult(vo);
    }
    
    @Override
    public ResponseResult logout() {
        // 获取userid
        Long userId = SecurityUtils.getUserId();
        // 删除Redis中的用户信息
        redisCache.deleteObject(RedisConstants.ADMIN_LOGIN_PREFIX + userId);
        
        return ResponseResult.okResult();
    }
}