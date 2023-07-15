package cn.edu.whut.gumorming.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog-Back
 * @Package : cn.edu.whut.gumorming.enums
 * @createTime : 2023/7/14 10:21
 * @Email : gumorming@163.com
 * @Description :
 */

@Getter
@AllArgsConstructor
public enum LoginTypeEnum {
    
    /**
     * 邮箱
     */
    EMAIL(1, "邮箱登录", ""),
    
    /**
     * QQ
     */
    QQ(2, "QQ登录", "qqLoginStrategyImpl"),
    
    /**
     * Gitee
     */
    GITEE(3, "Gitee登录", "giteeLoginStrategyImpl"),
    
    /**
     * Github
     */
    GITHUB(4, "Github登录", "githubLoginStrategyImpl");
    
    /**
     * 登录方式
     */
    private final Integer loginType;
    
    /**
     * 描述
     */
    private final String description;
    
    /**
     * 策略
     */
    private final String strategy;
}