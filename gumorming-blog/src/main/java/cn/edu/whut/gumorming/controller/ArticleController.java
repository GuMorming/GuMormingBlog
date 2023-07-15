package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.annotation.SystemLog;
import cn.edu.whut.gumorming.model.vo.article.ArticleSearchVO;
import cn.edu.whut.gumorming.model.vo.article.BlogArticleVO;
import cn.edu.whut.gumorming.model.vo.response.PageVo;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/6 17:19
 * @Email : gumorming@163.com
 * @Description :
 */

@RestController
@RequestMapping("/blog/article")
@Tag(name = "ArticleController", description = "文章相关接口")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    
    
    /**
     * 查询热门文章
     *
     * @return ResponseResult
     */
    @GetMapping("/topArticleList")
    @Operation(summary = "topArticleList", description = "获取Top文章")
    public ResponseResult topArticleList() {
        return articleService.topArticleList();
    }
    
    /**
     * 查询对应分类文章
     *
     * @param current 当前页数
     * @param size    页面文章数
     * @return ResponseResult
     */
    @GetMapping("/list")
    public ResponseResult<PageVo> pageArticleList(Integer current, Integer size) {
        return articleService.homePageArticleVO(current, size);
    }
    
    @GetMapping("/{id}")
    @SystemLog("获取文章详情")
    public ResponseResult<BlogArticleVO> getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }
    
    
    @GetMapping("/search")
    public ResponseResult<List<ArticleSearchVO>> searchPageArticleVO(String keyword) {
        return articleService.searchPageArticleVO(keyword);
    }
    
}