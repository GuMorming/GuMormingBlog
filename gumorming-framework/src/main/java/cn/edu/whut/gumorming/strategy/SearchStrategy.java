package cn.edu.whut.gumorming.strategy;


import cn.edu.whut.gumorming.model.vo.article.ArticleSearchVO;

import java.util.List;

/**
 * 文章搜索策略
 *
 * @author ican
 */
public interface SearchStrategy {
    
    /**
     * 搜索文章
     *
     * @param keyword 关键字
     * @return {@link List<ArticleSearchVO>} 文章列表
     */
    List<ArticleSearchVO> searchArticle(String keyword);
}