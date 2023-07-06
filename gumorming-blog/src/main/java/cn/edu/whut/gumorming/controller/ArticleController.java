package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

//    @GetMapping("/list")
//    public List<Article> test() {
//        return articleService.list();
//    }
    
    /**
     * 查询热门文章
     *
     * @return ResponseResult
     */
    @GetMapping("/topArticleList")
    public ResponseResult topArticleList() {
        ResponseResult result = articleService.topArticleList();
        return result;
    }
}