package cn.edu.whut.gumorming.handler.exception;

import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import cn.edu.whut.gumorming.exception.SystemException;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.handler.exception
 * @createTime : 2023/7/7 18:58
 * @Email : gumorming@163.com
 * @Description :
 */

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e) {
        // 打印异常信息
        log.error("自定义异常! {}", e.getMsg());
        // 从异常对象获取提示信息, 封装返回
        return ResponseResult.errorResult(e.getCode(), e.getMsg());
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e) {
        // 打印异常信息
        log.error("异常! {}", e.getMessage());
        // 从异常对象获取提示信息, 封装返回
        return ResponseResult.errorResult(HttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }
}