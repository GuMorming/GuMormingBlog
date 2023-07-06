package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.domain.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.mapper
 * @createTime : 2023/7/6 15:54
 * @Email : gumorming@163.com
 * @Description :
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}