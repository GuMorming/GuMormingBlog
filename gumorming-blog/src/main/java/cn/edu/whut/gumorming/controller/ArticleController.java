package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.annotation.SystemLog;
import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/6 17:19
 * @Email : gumorming@163.com
 * @Description :
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    
    
    /**
     * 查询热门文章
     *
     * @return ResponseResult
     */
    @GetMapping("/topArticleList")
    public ResponseResult topArticleList() {
        return articleService.topArticleList();
    }
    
    /**
     * 查询对应分类文章
     *
     * @param pageNum    当前页数
     * @param pageSize
     * @param categoryId
     * @return ResponseResult
     */
    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }
    
    @GetMapping("/{id}")
    @SystemLog("获取文章详情")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }
}