package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.domain.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author gumorming
 * @since 2023-07-09 19:10:44
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    
    List<String> selectRoleKeyByUserId(Long userId);
}