package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.EmailConstants;
import cn.edu.whut.gumorming.constants.RedisConstants;
import cn.edu.whut.gumorming.constants.SystemConstants;
import cn.edu.whut.gumorming.constants.UserConstants;
import cn.edu.whut.gumorming.entity.LoginUser;
import cn.edu.whut.gumorming.entity.SiteConfig;
import cn.edu.whut.gumorming.entity.User;
import cn.edu.whut.gumorming.entity.UserRole;
import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import cn.edu.whut.gumorming.enums.LoginTypeEnum;
import cn.edu.whut.gumorming.enums.RoleEnum;
import cn.edu.whut.gumorming.model.dto.mail.MailDTO;
import cn.edu.whut.gumorming.model.dto.user.RegisterDTO;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.BlogLoginService;
import cn.edu.whut.gumorming.service.SiteConfigService;
import cn.edu.whut.gumorming.service.UserRoleService;
import cn.edu.whut.gumorming.service.UserService;
import cn.edu.whut.gumorming.utils.JwtUtil;
import cn.edu.whut.gumorming.utils.RedisCache;
import cn.hutool.captcha.generator.RandomGenerator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @Autowired
    private UserService userService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SiteConfigService siteConfigService;
    @Autowired
    private UserRoleService userRoleService;
    
    
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        // 调用自定义  UserDetailsService
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 判断是否认证通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 获取userid生产token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        // 用户信息存入redis
        redisCache.setCacheObject(RedisConstants.BLOG_LOGIN_PREFIX + userId, loginUser);
        // 登录时间更新
        user.setLoginTime(new Date());
        userService.update(new LambdaUpdateWrapper<User>()
                .set(User::getLoginTime, new Date())
                .eq(User::getId, Long.parseLong(userId)));
        
        
        return ResponseResult.okResult(jwt);
    }
    
    /**
     * 登出, 删除Redis中的用户信息
     *
     * @return
     */
    @Override
    public ResponseResult logout() {
        // 获取token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 解析获取userid
        Long userId = loginUser.getUser().getId();
        // 删除Redis中的用户信息
        redisCache.deleteObject(RedisConstants.BLOG_LOGIN_PREFIX + userId);
        
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult<?> sendCode(String username) {
        // 对邮箱格式校验
        if (!StringUtils.hasText(username)) {
            return ResponseResult.errorResult(HttpCodeEnum.REGISTER_EMAIL_NULL);
        } else if (!isEmailValid(username)) {
            return ResponseResult.errorResult(HttpCodeEnum.REGISTER_EMAIL_VALID);
        } else if (isEmailExist(username)) {
            return ResponseResult.errorResult(HttpCodeEnum.EMAIL_EXIST);
        }
        // 生成6位随机验证码
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 6);
        String code = randomGenerator.generate();
        MailDTO mailDTO = MailDTO.builder()
                .toEmail(username)
                .subject(EmailConstants.CAPTCHA)
                .content("您的验证码为 " + code + " 有效期为" + RedisConstants.CODE_EXPIRE_TIME + "分钟")
                .build();
        // 验证码存入消息队列
        rabbitTemplate.convertAndSend(EmailConstants.EMAIL_EXCHANGE, EmailConstants.EMAIL_SIMPLE_KEY, mailDTO);
        // 验证码存入redis
        redisCache.setCacheObject(RedisConstants.CODE_KEY + username, code, RedisConstants.CODE_EXPIRE_TIME, TimeUnit.MINUTES);
        
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult register(RegisterDTO registerDTO) {
        // 1.校验
        // 用户名
        if (!StringUtils.hasText(registerDTO.getUsername())) {
            return ResponseResult.errorResult(HttpCodeEnum.REGISTER_USERNAME_NULL);
        }
        // 验证码
        String code = redisCache.getCacheObject(RedisConstants.CODE_KEY + registerDTO.getUsername());
        if (!code.equals(registerDTO.getCode())) {
            return ResponseResult.errorResult(HttpCodeEnum.EMAIL_CODE_INVALID);
        }
        // 邮箱
        if (!isEmailValid(registerDTO.getUsername())) {
            return ResponseResult.errorResult(HttpCodeEnum.REGISTER_EMAIL_VALID);
        } else if (isEmailExist(registerDTO.getUsername())) {
            return ResponseResult.errorResult(HttpCodeEnum.EMAIL_EXIST);
        }
        // 2.添加用户
        SiteConfig siteConfig = siteConfigService.getSiteConfig();
        User newUser = User.builder()
                .username(registerDTO.getUsername())
                .email(registerDTO.getUsername())
                .nickname(UserConstants.USER_NICKNAME + IdWorker.getId())
                .avatar(siteConfig.getUserAvatar())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .loginType(LoginTypeEnum.EMAIL.getLoginType())
                .isDisable(SystemConstants.FALSE)
                .build();
        userService.save(newUser);
        // 3.绑定用户角色
        UserRole userRole = UserRole.builder()
                .userId(newUser.getId())
                .roleId(RoleEnum.USER.getRoleId())
                .build();
        userRoleService.save(userRole);
        
        return ResponseResult.okResult();
    }
    
    /**
     * 检查邮箱格式
     *
     * @param username
     * @return
     */
    public static boolean isEmailValid(String username) {
        String rule = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        //正则表达式的模式 编译正则表达式
        Pattern p = Pattern.compile(rule);
        //正则表达式的匹配器
        Matcher m = p.matcher(username);
        //进行正则匹配
        return m.matches();
    }
    
    public boolean isEmailExist(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        long count = userService.count(queryWrapper);
        System.out.println("count: " + count);
        return count > 0;
    }
}