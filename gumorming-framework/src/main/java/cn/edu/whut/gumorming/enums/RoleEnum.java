package cn.edu.whut.gumorming.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog-Back
 * @Package : cn.edu.whut.gumorming.enums
 * @createTime : 2023/7/14 10:23
 * @Email : gumorming@163.com
 * @Description :
 */

@Getter
@AllArgsConstructor
public enum RoleEnum {
    /**
     * 管理员
     */
    ADMIN(1L, "admin"),
    /**
     * 用户
     */
    USER(2L, "user"),
    /**
     * 测试账号
     */
    TEST(3L, "test");
    
    /**
     * 角色id
     */
    private final Long roleId;
    
    /**
     * 描述
     */
    private final String name;
    
}