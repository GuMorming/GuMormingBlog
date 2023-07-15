package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户和角色关联表(UserRole)表数据库访问层
 *
 * @author gumorming
 * @since 2023-07-11 11:19:56
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}