package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.entity.LoginUser;
import cn.edu.whut.gumorming.domain.entity.User;
import cn.edu.whut.gumorming.domain.vo.BlogLoginUserVo;
import cn.edu.whut.gumorming.domain.vo.UserInfoVo;
import cn.edu.whut.gumorming.service.BlogLoginService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import cn.edu.whut.gumorming.utils.JwtUtil;
import cn.edu.whut.gumorming.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.service.impl
 * @createTime : 2023/7/7 14:21
 * @Email : gumorming@163.com
 * @Description :
 */
@Service("blogLoginService")
public class BlogLoginServiceImpl implements BlogLoginService {
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
        redisCache.setCacheObject("bloglogin:" + userId, loginUser);
        // token和userinfo封装,返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogLoginUserVo vo = new BlogLoginUserVo(jwt, userInfoVo);
        
        return ResponseResult.okResult(vo);
    }
}