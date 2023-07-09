package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.domain.entity.Role;
import cn.edu.whut.gumorming.mapper.RoleMapper;
import cn.edu.whut.gumorming.service.RoleService;
import cn.edu.whut.gumorming.utils.SecurityUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author gumorming
 * @since 2023-07-09 19:10:44
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    
    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        // 管理员, 返回集合中只需要有admin
        if (SecurityUtils.isAdmin()) {
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        // 否则,查询用户对应角色信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }
}