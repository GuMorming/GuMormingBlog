package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.domain.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签(Tag)表数据库访问层
 *
 * @author gumorming
 * @since 2023-07-09 15:55:49
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}