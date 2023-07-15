package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.Article;
import cn.edu.whut.gumorming.model.vo.article.ArticlePaginationVO;
import cn.edu.whut.gumorming.model.vo.article.ArticleSearchVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    /**
     * 查询上一篇文章
     *
     * @param articleId 文章id
     * @return 上一篇文章
     */
    ArticlePaginationVO selectLastArticle(Long articleId);
    
    /**
     * 查询下一篇文章
     *
     * @param articleId 文章id
     * @return 下一篇文章
     */
    ArticlePaginationVO selectNextArticle(Long articleId);
    
    /**
     * 文章搜索
     *
     * @param keyword 关键字
     * @return 文章列表
     */
    List<ArticleSearchVO> searchArticle(@Param("keyword") String keyword);
}