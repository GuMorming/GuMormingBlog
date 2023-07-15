package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.Friend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Friend)表数据库访问层
 *
 * @author gumorming
 * @since 2023-07-12 15:45:32
 */
@Mapper
public interface FriendMapper extends BaseMapper<Friend> {

}