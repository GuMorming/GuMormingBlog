package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.entity.UserRole;
import cn.edu.whut.gumorming.mapper.UserRoleMapper;
import cn.edu.whut.gumorming.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表(UserRole)表服务实现类
 *
 * @author gumorming
 * @since 2023-07-11 11:19:56
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}