package cn.edu.whut.gumorming.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.domain.vo
 * @createTime : 2023/7/9 19:07
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@Accessors(chain = true) // 链式编程, setter 返回值为自身
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserInfoVo {
    // 菜单
    private List<String> menus;
    // 权限
    private List<String> roles;
    // 用户信息
    private UserInfoVo userInfoVo;
}