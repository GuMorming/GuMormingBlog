package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.domain.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author gumorming
 * @since 2023-07-09 19:10:43
 */
public interface RoleService extends IService<Role> {
    
    List<String> selectRoleKeyByUserId(Long id);
}