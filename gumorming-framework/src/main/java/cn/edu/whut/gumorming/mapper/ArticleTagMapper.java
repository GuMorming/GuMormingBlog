package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.ArticleTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章标签关联表(ArticleTag)表数据库访问层
 *
 * @author gumorming
 * @since 2023-07-10 14:51:51
 */
@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

}