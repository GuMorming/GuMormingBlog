package cn.edu.whut.gumorming.strategy.context;


import cn.edu.whut.gumorming.model.vo.article.ArticleSearchVO;
import cn.edu.whut.gumorming.strategy.SearchStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static cn.edu.whut.gumorming.enums.SearchModeEnum.getStrategy;

/**
 * 搜索策略上下文
 */
@Service
public class SearchStrategyContext {
    
    /**
     * 搜索模式
     */
    @Value("${search.mode}")
    private String searchMode;
    
    @Autowired
    private Map<String, SearchStrategy> searchStrategyMap;
    
    /**
     * 执行搜索策略
     *
     * @param keyword 关键字
     * @return {@link List <ArticleSearchVO>} 搜索文章
     */
    public List<ArticleSearchVO> executeSearchStrategy(String keyword) {
        return searchStrategyMap.get(getStrategy(searchMode)).searchArticle(keyword);
    }
    
}