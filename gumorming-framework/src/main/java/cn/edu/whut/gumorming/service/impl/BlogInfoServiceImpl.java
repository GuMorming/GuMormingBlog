package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.RedisConstants;
import cn.edu.whut.gumorming.entity.Article;
import cn.edu.whut.gumorming.entity.SiteConfig;
import cn.edu.whut.gumorming.enums.ArticleStatusEnum;
import cn.edu.whut.gumorming.model.vo.article.ArticleRankVO;
import cn.edu.whut.gumorming.model.vo.blog.BlogBackInfoVO;
import cn.edu.whut.gumorming.model.vo.blog.BlogInfoVO;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.*;
import cn.edu.whut.gumorming.utils.RedisCache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 博客业务接口实现类
 **/
@Service
public class BlogInfoServiceImpl implements BlogInfoService {
    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private TagService tagService;
    
    @Autowired
    private RedisCache redisCache;
    
    @Autowired
    private SiteConfigService siteConfigService;

//    @Autowired
//    private MessageMapper messageMapper;
    
    @Autowired
    private UserService userService;

//    @Autowired
//    private VisitLogMapper visitLogMapper;
    
    @Autowired
    private HttpServletRequest request;
    
    // todo
    @Override
    public void report() {
//        // 获取用户ip
//        String ipAddress = IpUtils.getIpAddress(request);
//        Map<String, String> userAgentMap = UserAgentUtils.parseOsAndBrowser(request.getHeader("User-Agent"));
//        // 获取访问设备
//        String browser = userAgentMap.get("browser");
//        String os = userAgentMap.get("os");
//        // 生成唯一用户标识
//        String uuid = ipAddress + browser + os;
//        String md5 = DigestUtils.md5DigestAsHex(uuid.getBytes());
//        // 判断是否访问
//        if (!redisCache.hasSetValue(RedisConstant.UNIQUE_VISITOR, md5)) {
//            // 访问量+1
//            redisCache.incr(RedisConstant.BLOG_VIEW_COUNT, 1);
//            // 保存唯一标识
//            redisCache.setSet(RedisConstant.UNIQUE_VISITOR, md5);
//        }
    }
    
    @Override
    public ResponseResult<BlogInfoVO> getBlogInfo() {
        // 文章数量(公开)
        Long articleCount = articleService.count(
                new LambdaQueryWrapper<Article>()
                        .eq(Article::getStatus, ArticleStatusEnum.PUBLIC.getStatus())
//                        .eq(Article::getIsDelete, Boolean.FALSE)
        );
        // 分类数量
        Long categoryCount = categoryService.count(null);
        // 标签数量
        Long tagCount = tagService.count(null);
        // todo 博客访问量
//        Integer count = redisCache.getCacheObject(RedisConstants.BLOG_VIEW_COUNT);
//        String viewCount = Optional.ofNullable(count).orElse(0).toString();
        // 网站配置
        SiteConfig siteConfig = siteConfigService.getSiteConfig();
        
        return ResponseResult.okResult(BlogInfoVO.builder()
                        .articleCount(articleCount)
                        .categoryCount(categoryCount)
                        .tagCount(tagCount)
//                .viewCount(viewCount)
                        .siteConfig(siteConfig)
                        .build()
        );
    }
    
    @Override
    public BlogBackInfoVO getBlogBackInfo() {
//        访问量
        Integer viewCount = redisCache.getCacheObject(RedisConstants.BLOG_VIEW_COUNT);
        // 留言量
//        Long messageCount = messageMapper.selectCount(null);
        // 用户量
        Long userCount = userService.count(null);
        // 文章量
        Long articleCount = articleService.count(new LambdaQueryWrapper<Article>().eq(Article::getIsDelete, Boolean.FALSE));
        // 分类数据
//        List<CategoryVO> categoryVOList = categoryService.selectCategoryVO();
        // 标签数据
//        List<TagOptionVO> tagVOList = tagService.selectTagOptionList();
        // 查询用户浏览
//        DateTime startTime = DateUtil.beginOfDay(DateUtil.offsetDay(new Date(), -7));
//        DateTime endTime = DateUtil.endOfDay(new Date());
//        List<UserViewVO> userViewVOList = visitLogMapper.selectUserViewList(startTime, endTime);
        // 文章统计
//        List<ArticleStatisticsVO> articleStatisticsList = articleService.selectArticleStatistics();
        // 查询redis访问量前五的文章
//        Map<Object, Double> articleMap = redisCache.zReverseRangeWithScore(RedisConstant.ARTICLE_VIEW_COUNT, 0, 4);
        
        BlogBackInfoVO blogBackInfoVO = BlogBackInfoVO.builder()
//                .articleStatisticsList(articleStatisticsList)
//                .tagVOList(tagVOList)
                .viewCount(viewCount)
//                .messageCount(messageCount)
                .userCount(userCount)
                .articleCount(articleCount)
//                .categoryVOList(categoryVOList)
//                .userViewVOList(userViewVOList)
                .build();
//        if (CollectionUtils.isNotEmpty(articleMap)) {
//            // 查询文章排行
//            List<ArticleRankVO> articleRankVOList = listArticleRank(articleMap);
//            blogBackInfoVO.setArticleRankVOList(articleRankVOList);
//        }
        return blogBackInfoVO;
    }
    
    @Override
    public String getAbout() {
        SiteConfig siteConfig = redisCache.getCacheObject(RedisConstants.SITE_SETTING);
        return siteConfig.getAboutMe();
    }
    
    /**
     * 查询文章排行
     *
     * @param articleMap 文章浏览量信息
     * @return {@link List<ArticleRankVO>} 文章排行
     */
    private List<ArticleRankVO> listArticleRank(Map<Object, Double> articleMap) {
        // 提取文章id
        List<Integer> articleIdList = new ArrayList<>(articleMap.size());
        articleMap.forEach((key, value) -> articleIdList.add((Integer) key));
        // 查询文章信息
        List<Article> articleList = articleService.list(
                new LambdaQueryWrapper<Article>()
                        .select(Article::getId, Article::getArticleTitle)
                        .in(Article::getId, articleIdList)
        );
        return articleList.stream()
                .map(article -> ArticleRankVO.builder()
                        .articleTitle(article.getArticleTitle())
                        .viewCount(articleMap.get(article.getId()).intValue())
                        .build())
                .sorted(Comparator.comparingInt(ArticleRankVO::getViewCount).reversed())
                .collect(Collectors.toList());
    }
}