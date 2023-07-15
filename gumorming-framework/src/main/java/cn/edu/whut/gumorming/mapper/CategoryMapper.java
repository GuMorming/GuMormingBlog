package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 分类表(Category)表数据库访问层
 *
 * @author gumorming
 * @since 2023-07-06 22:09:45
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}