package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.SystemConstants;
import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.entity.LoginUser;
import cn.edu.whut.gumorming.domain.entity.User;
import cn.edu.whut.gumorming.domain.vo.AdminLoginUserVo;
import cn.edu.whut.gumorming.service.AdminLoginService;
import cn.edu.whut.gumorming.utils.JwtUtil;
import cn.edu.whut.gumorming.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
    
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        
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
        redisCache.setCacheObject(SystemConstants.REDIS_KEY_PREFIX_ADMIN_LOGIN + userId, loginUser);
        // token封装,返回
        AdminLoginUserVo vo = new AdminLoginUserVo(jwt);
        
        return ResponseResult.okResult(vo);
    }
    
    @Override
    public ResponseResult logout() {
        // 获取token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 解析获取userid
        Long userId = loginUser.getUser().getId();
        // 删除Redis中的用户信息
        redisCache.deleteObject(SystemConstants.REDIS_KEY_PREFIX_BLOG_LOGIN + userId);
        
        return ResponseResult.okResult();
    }
}