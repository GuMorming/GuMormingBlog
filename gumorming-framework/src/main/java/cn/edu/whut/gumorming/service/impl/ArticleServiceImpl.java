package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.SystemConstants;
import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.entity.Article;
import cn.edu.whut.gumorming.domain.entity.Category;
import cn.edu.whut.gumorming.domain.vo.ArticleDetailVo;
import cn.edu.whut.gumorming.domain.vo.ArticleListVo;
import cn.edu.whut.gumorming.domain.vo.PageVo;
import cn.edu.whut.gumorming.domain.vo.TopArticleVo;
import cn.edu.whut.gumorming.mapper.ArticleMapper;
import cn.edu.whut.gumorming.mapper.CategoryMapper;
import cn.edu.whut.gumorming.service.ArticleService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


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
    
    /**
     * 查询热门文章, 封装成ResponseResult返回
     */
    @Override
    public ResponseResult topArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 非草稿
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 按浏览量排序
        queryWrapper.orderByDesc(Article::getViewCount);
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
     * 返回分类Id下的分页数目Id
     *
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 1. 查询条件
        // 若有 categoryId, 则查询对应分类下的文章
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
        // 正式发布的文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 置顶文章在最前
        queryWrapper.orderByDesc(Article::getIsTop);
        // 2.分页
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        // 查询categoryName
        List<Article> articles = page.getRecords();
        // type 1: 循环实现
//        for (Article article : articles) {
//            Category category = categoryMapper.selectById(article.getCategoryId());
//            article.setCategoryName(category.getName());
//        }
        // type 2: stream流实现
        articles.stream()
                .map(article -> article.setCategoryName(categoryMapper.selectById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());
        // 封装VOs
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        
        
        return ResponseResult.okResult(pageVo);
    }
    
    /**
     * 根据文章Id查询文章
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult getArticleDetail(Long id) {
        // 查询文章
        Article article = getById(id);
        // 封装Vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        // 根据分类Id,获取文章内容
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryMapper.selectById(categoryId);
        if (category != null) {
            articleDetailVo.setCategoryName(category.getName());
        }
        
        return ResponseResult.okResult(articleDetailVo);
    }
}