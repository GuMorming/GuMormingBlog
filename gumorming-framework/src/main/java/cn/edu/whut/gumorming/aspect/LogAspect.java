package cn.edu.whut.gumorming.aspect;

import cn.edu.whut.gumorming.annotation.SystemLog;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.aspect
 * @createTime : 2023/7/8 20:26
 * @Email : gumorming@163.com
 * @Description :
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    // 切点
    @Pointcut("@annotation(cn.edu.whut.gumorming.annotation.SystemLog)")
    public void pt() {
    
    }
    
    // 通知方法
    @Around("pt()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object ret;
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed();
            handleAfter(ret);
        } finally {
            // 结束后换行
            log.info("=======End=======" + System.lineSeparator());
        }
        return ret;
    }
    
    
    private void handleBefore(ProceedingJoinPoint joinPoint) {
        // 获取 request对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 获取被增强方法上的注解对象
        SystemLog systemLog = getSystemLog(joinPoint);
        
        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL            : {}", request.getRequestURL());
        // 打印描述信息(业务接口名)
        log.info("BusinessName   : {}", systemLog.value());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), ((MethodSignature) joinPoint.getSignature()).getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args   : {}", JSON.toJSON(joinPoint.getArgs()));
        
    }
    
    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        SystemLog systemLog = methodSignature.getMethod().getAnnotation(SystemLog.class);
        return systemLog;
    }
    
    private void handleAfter(Object ret) {
        // 打印出参
        log.info("Response       : {}", JSON.toJSON(ret));
    }
}