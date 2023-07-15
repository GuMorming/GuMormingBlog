package cn.edu.whut.gumorming.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/11 11:15
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminAddUserDTO {
    //主键@TableId
    private Long id;
    
    //用户名
    private String username;
    //昵称
    private String nickname;
    //密码
    private String password;
    
    //账号状态（0正常 1停用）
    private String isDisable;
    //邮箱
    private String email;
    //手机号
    private String phoneNumber;
    //用户性别（0男，1女，2未知）
    private Integer sex;
    // 角色
    List<Long> roleIds;
    
    
}