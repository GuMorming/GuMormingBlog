package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 用户表(User)表数据库访问层
 *
 * @author gumorming
 * @since 2023-07-07 13:49:38
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}