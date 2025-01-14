package cn.edu.whut.gumorming.config;

import cn.edu.whut.gumorming.filter.JwtAuthenticationTokenFilter;
import cn.edu.whut.gumorming.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.config
 * @createTime : 2023/7/7 14:23
 * @Email : gumorming@163.com
 * @Description :
 */
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class AdminSecurityConfig {
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    AccessDeniedHandler accessDeniedHandler;
    private static final String[] SWAGGER_WHITELIST = {
            "/api/v1/auth/**"
            , "/v3/api-docs/**"
            , "/v3/api-docs.yaml"
            , "/swagger-ui/**"
            , "/swagger-ui.html"
    };
    private static final String[] KNIFE4J_WHITELIST = {
            "/webjars/"
            , "/webjars/**"
            , "/doc.html/"
            , "/doc.html#/"
            , "/doc.html/**"
    };
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                //关闭csrf
                .csrf(AbstractHttpConfigurer::disable)
                //不通过Session获取SecurityContext
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // 登录接口 允许匿名访问
                        .requestMatchers("/user/login").anonymous()
                        .requestMatchers(SWAGGER_WHITELIST).anonymous()
                        .requestMatchers(KNIFE4J_WHITELIST).anonymous()
                        // 除登录接口皆须认证
                        .anyRequest().authenticated()
                )
                .logout(AbstractHttpConfigurer::disable)
                //允许跨域
                .cors(Customizer.withDefaults())
                // 添加登录校验过滤器
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(handler -> handler
                        .accessDeniedHandler(accessDeniedHandler)
                        .authenticationEntryPoint(authenticationEntryPoint))
                .build();
    }
    
    @Bean
    AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // 自定义 UserDetailsService
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        // 配置password加密 BCrypt
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoAuthenticationProvider);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
}