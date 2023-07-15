package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.Link;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 友链(Link)表数据库访问层
 *
 * @author gumorming
 * @since 2023-07-07 13:23:36
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}