package cn.edu.whut.gumorming.job;

import cn.edu.whut.gumorming.constants.RedisConstants;
import cn.edu.whut.gumorming.entity.Article;
import cn.edu.whut.gumorming.service.ArticleService;
import cn.edu.whut.gumorming.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.job
 * @createTime : 2023/7/9 10:03
 * @Email : gumorming@163.com
 * @Description :
 */
@Component
public class ViewCountJob {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ArticleService articleService;
    
    /**
     * 每隔 10分钟 从Redis更新一次MySQL
     */
    // 10 s
//    @Scheduled(cron = "0/10 * * * * ?")
    // 10 min
    @Scheduled(cron = "0 0/10 * * * ?")
    public void updateViewCountToMySQL() {
        System.out.println("定时更新文章浏览量");
        // 获取Redis中的浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap(RedisConstants.ARTICLE_VIEW_COUNT);
        // Map转换为 Article List
        List<Article> articles = viewCountMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .toList();
        // 更新到数据库
        articleService.updateBatchById(articles);
    }
}