package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.entity.Article;
import cn.edu.whut.gumorming.domain.vo.TopArticleVo;
import cn.edu.whut.gumorming.mapper.ArticleMapper;
import cn.edu.whut.gumorming.service.ArticleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.service.impl
 * @createTime : 2023/7/6 15:56
 * @Email : gumorming@163.com
 * @Description :
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    /**
     * 查询热门文章, 封装成ResponseResult返回
     */
    @Override
    public ResponseResult topArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 非草稿
        queryWrapper.eq(Article::getStatus, 0);
        // 按浏览量排序
        queryWrapper.orderByDesc(Article::getViewCount);
        // Top 10
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);
        List<Article> articles = page.getRecords();
        // bean拷贝
        List<TopArticleVo> articleVos = new ArrayList<>();
        for (Article article : articles) {
            TopArticleVo vo = new TopArticleVo();
            BeanUtils.copyProperties(article, vo);
            articleVos.add(vo);
        }
        // 封装
        return ResponseResult.okResult(articleVos);
    }
}