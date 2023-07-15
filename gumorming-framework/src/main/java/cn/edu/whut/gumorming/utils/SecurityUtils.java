package cn.edu.whut.gumorming.utils;

import cn.edu.whut.gumorming.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author 三更
 * <p>
 * 主要从 SecurityContextHolder 中获取Token
 */
public class SecurityUtils {
    
    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() {
        return (LoginUser) getAuthentication().getPrincipal();
    }
    
    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
    public static Boolean isAdmin() {
        Long id = getLoginUser().getUser().getId();
        return id != null && 1L == id;
    }
    
    public static Long getUserId() {
        return getLoginUser().getUser().getId();
    }
}