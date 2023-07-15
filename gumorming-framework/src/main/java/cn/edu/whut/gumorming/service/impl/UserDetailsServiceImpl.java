package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.SystemConstants;
import cn.edu.whut.gumorming.entity.LoginUser;
import cn.edu.whut.gumorming.entity.User;
import cn.edu.whut.gumorming.mapper.MenuMapper;
import cn.edu.whut.gumorming.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.service.impl
 * @createTime : 2023/7/7 14:37
 * @Email : gumorming@163.com
 * @Description :
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    
    /**
     * @param username authenticationManager传来
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        // 根据用户名查询用户信息
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        // 判断是否查到用户,没查到则抛出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }
        //  后台用户需要查询权限并封装
        List<String> perms = null;
        if (!SystemConstants.IS_BLOG_LOGIN && user.getId().equals(1L)) {
            perms = menuMapper.selectPermsByUserId(user.getId());
        }
        // 返回用户信息
        return new LoginUser(user, perms);
    }
}