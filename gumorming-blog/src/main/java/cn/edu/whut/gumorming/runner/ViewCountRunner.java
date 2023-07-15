package cn.edu.whut.gumorming.runner;

import cn.edu.whut.gumorming.constants.RedisConstants;
import cn.edu.whut.gumorming.constants.SystemConstants;
import cn.edu.whut.gumorming.entity.Article;
import cn.edu.whut.gumorming.mapper.ArticleMapper;
import cn.edu.whut.gumorming.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.runner
 * @createTime : 2023/7/9 9:39
 * @Email : gumorming@163.com
 * @Description :
 */

/**
 * 启动时将article表viewCount存入Redis中
 */
@Component
public class ViewCountRunner implements CommandLineRunner {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisCache redisCache;
    
    @Override
    public void run(String... args) {
        // 启动时将博客登录状态置为 true
        SystemConstants.IS_BLOG_LOGIN = true;
        // 查询博客信息 id viewCount
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> article.getViewCount().intValue()));
        // 存储到Redis中
        redisCache.setCacheMap(RedisConstants.ARTICLE_VIEW_COUNT, viewCountMap);
    }
}