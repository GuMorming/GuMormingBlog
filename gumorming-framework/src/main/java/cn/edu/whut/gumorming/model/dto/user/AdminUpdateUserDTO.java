package cn.edu.whut.gumorming.model.dto.user;

import lombok.Data;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog-Back
 * @Package : cn.edu.whut.gumorming.model.dto.user
 * @createTime : 2023/7/14 14:44
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
public class AdminUpdateUserDTO {
    //主键@TableId
    private Long id;
    
    //用户名
    private String username;
    //昵称
    private String nickname;
    
    //账号状态（0正常 1停用）
    private Integer isDisable;
    //邮箱
    private String email;
    
    //用户性别（0男，1女，2未知）
    private Integer sex;
    // 角色
    List<Long> roleIds;
}