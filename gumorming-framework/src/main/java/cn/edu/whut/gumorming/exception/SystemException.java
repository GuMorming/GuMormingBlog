package cn.edu.whut.gumorming.exception;

import cn.edu.whut.gumorming.domain.enums.AppHttpCodeEnum;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.exception
 * @createTime : 2023/7/7 18:56
 * @Email : gumorming@163.com
 * @Description :
 */

public class SystemException extends RuntimeException {
    private int code;
    
    private String msg;
    
    public int getCode() {
        return code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
}