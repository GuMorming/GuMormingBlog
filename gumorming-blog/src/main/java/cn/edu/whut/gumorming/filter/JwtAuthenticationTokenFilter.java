package cn.edu.whut.gumorming.filter;

import cn.edu.whut.gumorming.constants.RedisConstants;
import cn.edu.whut.gumorming.entity.LoginUser;
import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.utils.JwtUtil;
import cn.edu.whut.gumorming.utils.RedisCache;
import cn.edu.whut.gumorming.utils.WebUtils;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.filter
 * @createTime : 2023/7/7 17:01
 * @Email : gumorming@163.com
 * @Description :
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中的Token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            // 说明该接口不需要登录, 直接放行
            filterChain.doFilter(request, response);
            return;
        }
        // 解析获取UserId
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            // token 超时或非法
            // 响应告诉前端需要重新登录, 前端将当前用户Token清除,强制下线
            ResponseResult result = ResponseResult.errorResult(HttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        String userId = claims.getSubject();
        // 从Redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject(RedisConstants.BLOG_LOGIN_PREFIX + userId);
        // 如果获取不到
        if (Objects.isNull(loginUser)) {
            // 登录过期，提示重新登录
            ResponseResult result = ResponseResult.errorResult(HttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        // 存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        
        filterChain.doFilter(request, response);
    }
}