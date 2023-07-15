package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.RedisConstants;
import cn.edu.whut.gumorming.constants.SystemConstants;
import cn.edu.whut.gumorming.entity.Article;
import cn.edu.whut.gumorming.entity.ArticleTag;
import cn.edu.whut.gumorming.entity.Tag;
import cn.edu.whut.gumorming.enums.ArticleStatusEnum;
import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import cn.edu.whut.gumorming.mapper.ArticleMapper;
import cn.edu.whut.gumorming.mapper.CategoryMapper;
import cn.edu.whut.gumorming.mapper.TagMapper;
import cn.edu.whut.gumorming.model.dto.article.AdminAddArticleDTO;
import cn.edu.whut.gumorming.model.dto.article.QueryArticleDto;
import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.vo.article.*;
import cn.edu.whut.gumorming.model.vo.category.CategoryOptionVO;
import cn.edu.whut.gumorming.model.vo.response.PageVo;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.model.vo.tag.TagOptionVO;
import cn.edu.whut.gumorming.service.ArticleService;
import cn.edu.whut.gumorming.service.ArticleTagService;
import cn.edu.whut.gumorming.strategy.context.SearchStrategyContext;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import cn.edu.whut.gumorming.utils.RedisCache;
import cn.edu.whut.gumorming.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;


/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.service.impl
 * @createTime : 2023/7/6 15:56
 * @Email : gumorming@163.com
 * @Description :
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private SearchStrategyContext searchStrategyContext;
    
    /**
     * 查询热门文章, 封装成ResponseResult返回
     */
    @Override
    public ResponseResult topArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 非草稿
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // todo 按浏览量排序
//        queryWrapper.orderByDesc(Article::getViewCount);
        // Top 10
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);
        List<Article> articles = page.getRecords();
        // bean拷贝
//        List<TopArticleVo> articleVos = new ArrayList<>();
//        for (Article article : articles) {
//            TopArticleVo vo = new TopArticleVo();
//            BeanUtils.copyProperties(article, vo);
//            articleVos.add(vo);
//        }
        List<TopArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, TopArticleVo.class);
        // 封装
        return ResponseResult.okResult(articleVos);
    }
    
    /**
     * 展示主页文章
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResponseResult<PageVo> homePageArticleVO(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 查询条件:
        queryWrapper
                // 公开状态文章
                .eq(Article::getStatus, ArticleStatusEnum.PUBLIC.getStatus())
                .orderByDesc(Article::getIsTop)
                .orderByDesc(Article::getCreateTime);
        // 分页
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        List<Article> articles = page.getRecords();
        // 封装首页文章VO
        List<ArticleCardVO> articleCardVOS = BeanCopyUtils.copyBeanList(articles, ArticleCardVO.class);
        articleCardVOS = setCategoryAndTags(articleCardVOS);
        // 分页VO
        return ResponseResult.okResult(new PageVo<>(articleCardVOS, page.getTotal()));
    }
    
    /**
     * 根据文章Id查询文章
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult<BlogArticleVO> getArticleDetail(Long id) {
        ArticleMapper articleMapper = getBaseMapper();
        // 查询文章
        Article article = getById(id);
        // 获取上一篇文章
        ArticlePaginationVO lastArticle = articleMapper.selectLastArticle(id);
        // 获取下一篇文章
        ArticlePaginationVO nextArticle = articleMapper.selectNextArticle(id);
        // 根据分类Id获取分类名称
        CategoryOptionVO categoryOptionVO = BeanCopyUtils.copyBean(categoryMapper.selectById(article.getCategoryId()), CategoryOptionVO.class);
        // 根据标签Id获取标签集合
        List<TagOptionVO> tagOptionVOS = articleTagService.list(new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getArticleId, id))
                .stream()
                .map(ArticleTag::getTagId)
                .map(tagId -> {
                    Tag tag = tagMapper.selectById(tagId);
                    return new TagOptionVO(tagId, tag.getTagName());
                }).toList();
        // 更新redis浏览量
        redisCache.incrementCacheMapValue(RedisConstants.ARTICLE_VIEW_COUNT, id.toString(), 1);
        // 封装Vo
        BlogArticleVO blogArticleVo = BeanCopyUtils.copyBean(article, BlogArticleVO.class);
        blogArticleVo.setCategory(categoryOptionVO);
        blogArticleVo.setTagVOList(tagOptionVOS);
        blogArticleVo.setLastArticle(lastArticle);
        blogArticleVo.setNextArticle(nextArticle);
        
        return ResponseResult.okResult(blogArticleVo);
    }
    
    @Override
    public ResponseResult updateArticleViewCount(Long id) {
        // 更新Redis浏览量 +1
        redisCache.incrementCacheMapValue(RedisConstants.ARTICLE_VIEW_COUNT, id.toString(), 1);
        
        return ResponseResult.okResult();
    }
    
    @Override
    @Transactional // 事务操作
    public ResponseResult add(AdminAddArticleDTO adminAddArticleDTO) {
        // 添加 文章
        Article article = BeanCopyUtils.copyBean(adminAddArticleDTO, Article.class);
        article.setUserId(SecurityUtils.getUserId());
        if (Objects.isNull(article.getCategoryId())) {
            return ResponseResult.errorResult(HttpCodeEnum.REQUIRE_CATEGORY);
        }
        save(article);
        // 添加 文章和标签 的关联
        List<ArticleTag> articleTags = adminAddArticleDTO.getTagIds().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .toList();
        articleTagService.saveBatch(articleTags);
        
        return ResponseResult.okResult();
    }
    
    /**
     * 后台获取文章列表
     *
     * @param pageNum
     * @param pageSize
     * @param queryArticleDto 搜索条件
     * @return
     */
    @Override
    public ResponseResult getAdminArticleList(Integer pageNum, Integer pageSize, QueryArticleDto queryArticleDto) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 1. 查询条件
        queryWrapper
                // 去除已删除文章
                .eq(Article::getIsDelete, SystemConstants.FALSE)
                // 若有 标题 条件 则查询
                .like(StringUtils.hasText(queryArticleDto.getTitle()) && !queryArticleDto.getTitle().equals(""), Article::getArticleTitle, queryArticleDto.getTitle())
                .or()
                // 若有 文章摘要 条件 则查询
                .like(StringUtils.hasText(queryArticleDto.getSummary()) && !queryArticleDto.getSummary().equals(""), Article::getArticleSummary, queryArticleDto.getSummary())
                // 置顶文章在最前
                .orderByDesc(Article::getCreateTime);
        // 2.分页
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        // 封装VOs
        List<AdminArticleVO> adminArticleVOS = BeanCopyUtils.copyBeanList(page.getRecords(), AdminArticleVO.class);
        PageVo<AdminArticleVO> pageVo = new PageVo<>(adminArticleVOS, page.getTotal());
        
        
        return ResponseResult.okResult(pageVo);
    }
    
    @Override
    public ResponseResult getArticleById(Long id) {
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 根据id获取 article 对象
        Article article = getById(id);
//        QueryArticleVo queryArticleVo = BeanCopyUtils.copyBean(article, QueryArticleVo.class);
        AdminAddArticleDTO adminAddArticleDTO = BeanCopyUtils.copyBean(article, AdminAddArticleDTO.class);
        // 根据分类Id 查询分类名称
//        String categoryName = categoryMapper.selectById(queryArticleVo.getCategoryId()).getName();
//        queryArticleVo.setCategoryName(categoryName);
//        String categoryName = categoryMapper.selectById(addArticleDto.getCategoryId()).getCategoryName();
//        addArticleDto.setCategoryName(categoryName);
        // 获取 文章和标签 关系
//        articleTagLambdaQueryWrapper.eq(ArticleTag::getArticleId, queryArticleVo.getId());
        articleTagLambdaQueryWrapper.eq(ArticleTag::getArticleId, adminAddArticleDTO.getId());
        List<Long> tagIds = articleTagService.list(articleTagLambdaQueryWrapper).stream()
                .map(ArticleTag::getTagId)
                .toList();
        adminAddArticleDTO.setTagIds(tagIds);
//        List<String> tagNames = new ArrayList<>();
//        for (Long tagId : tagIds) {
//            String tagName = tagService.getById(tagId).getName();
//            tagNames.add(tagName);
//        }
//        queryArticleVo.setTags(tagNames);

//        return ResponseResult.okResult(queryArticleVo);
        return ResponseResult.okResult(adminAddArticleDTO);
    }
    
    /**
     * 根据文章id更新文章信息
     *
     * @param adminAddArticleDTO 要更新的文章信息
     * @return
     */
    @Override
    @Transactional
    public ResponseResult updateArticleById(AdminAddArticleDTO adminAddArticleDTO) {
        // 更新 文章
        Article article = BeanCopyUtils.copyBean(adminAddArticleDTO, Article.class);
        if (Objects.isNull(article.getCategoryId())) {
            return ResponseResult.errorResult(HttpCodeEnum.REQUIRE_CATEGORY);
        }
        updateById(article);
        // 清除文章和标签 关联
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId, article.getId());
        articleTagService.remove(queryWrapper);
        // 添加 文章和标签 的关联
        List<ArticleTag> articleTags = adminAddArticleDTO.getTagIds().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .toList();
        articleTagService.saveBatch(articleTags);
        
        return ResponseResult.okResult();
    }
    
    /**
     * 逻辑删除
     *
     * @param id 要删除的文章id
     * @return
     */
    @Override
    @Transactional
    public ResponseResult deleteArticle(List<Long> ids) {
        LambdaUpdateWrapper<Article> wrapper = new LambdaUpdateWrapper<>();
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        for (Long id : ids) {
            Article article = getById(id);
            
            wrapper.clear();
            wrapper.eq(Article::getId, id).set(Article::getIsDelete, Boolean.TRUE);
            update(article, wrapper);
            // 清除文章和标签 关联
            queryWrapper.clear();
            queryWrapper.eq(ArticleTag::getArticleId, article.getId());
            articleTagService.remove(queryWrapper);
        }
        
        return ResponseResult.okResult();
    }
    
    /**
     * 设置 ArticleCardVO的分类和标签
     */
    @Override
    public List<ArticleCardVO> setCategoryAndTags(List<ArticleCardVO> articleCardVOS) {
        // 获取分类名
        articleCardVOS = articleCardVOS
                .stream()
                .map(articleHomeVO -> articleHomeVO.setCategory(BeanCopyUtils.copyBean(categoryMapper.selectById(getById(articleHomeVO.getId()).getCategoryId()), CategoryOptionVO.class)))
                .toList();
        // 获取标签名
        articleCardVOS = articleCardVOS.stream()
                .map(articleHomeVO -> {
                    List<Long> tagIds = articleTagService
                            .list(new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getArticleId, articleHomeVO.getId()))
                            .stream()
                            .map(ArticleTag::getTagId)
                            .toList();
                    List<TagOptionVO> tagOptionVOS = tagIds.stream()
                            .map(tagId -> {
                                Tag tag = tagMapper.selectById(tagId);
                                return new TagOptionVO(tagId, tag.getTagName());
                            }).toList();
                    return articleHomeVO.setTagVOList(tagOptionVOS);
                })
                .toList();
        
        return articleCardVOS;
    }
    
    @Override
    public ResponseResult<PageVo<ArticleCardVO>> getTimelineList(GetParamsDTO getParamsDTO) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 查询条件
        queryWrapper
                // 公开状态文章
                .eq(Article::getStatus, ArticleStatusEnum.PUBLIC.getStatus())
                // 按创建时间降序
                .orderByDesc(Article::getCreateTime);
        Page<Article> page = new Page<>(getParamsDTO.getCurrent(), getParamsDTO.getSize());
        page(page, queryWrapper);
        List<Article> articles = page.getRecords();
        // 封装首页文章VO
        List<ArticleCardVO> articleCardVOS = BeanCopyUtils.copyBeanList(articles, ArticleCardVO.class);
        articleCardVOS = setCategoryAndTags(articleCardVOS);
        // 分页VO
        return ResponseResult.okResult(new PageVo<ArticleCardVO>(articleCardVOS, page.getTotal()));
    }
    
    @Override
    public ResponseResult<List<ArticleSearchVO>> searchPageArticleVO(String keyword) {
        List<ArticleSearchVO> articleSearchVOS = searchStrategyContext.executeSearchStrategy(keyword);
        
        return ResponseResult.okResult(articleSearchVOS);
    }
}