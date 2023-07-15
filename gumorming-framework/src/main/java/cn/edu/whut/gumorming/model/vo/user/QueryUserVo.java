package cn.edu.whut.gumorming.model.vo.user;

import cn.edu.whut.gumorming.entity.User;
import cn.edu.whut.gumorming.model.vo.userrole.RoleVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.domain.vo
 * @createTime : 2023/7/11 13:20
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryUserVo {
    User user;
    // 该用户的角色信息
    List<Long> roleIds;
    // 所有角色信息
    List<RoleVO> roles;
}