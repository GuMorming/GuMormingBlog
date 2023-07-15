package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.annotation.SystemLog;
import cn.edu.whut.gumorming.model.dto.QueryArticleDto;
import cn.edu.whut.gumorming.model.dto.article.AdminAddArticleDTO;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/10 14:31
 * @Email : gumorming@163.com
 * @Description :
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    
    @GetMapping("/list")
    @SystemLog("后台获取文章列表")
    public ResponseResult list(Integer pageNum, Integer pageSize, QueryArticleDto queryArticleDto) {
        return articleService.getAdminArticleList(pageNum, pageSize, queryArticleDto);
    }
    
    @GetMapping("/{id}")
    @SystemLog("后台查询文章详情")
    public ResponseResult getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }
    
    @PutMapping
    @SystemLog("后台更新文章")
    public ResponseResult updateArticleById(@RequestBody AdminAddArticleDTO adminAddArticleDTO) {
        return articleService.updateArticleById(adminAddArticleDTO);
    }
    
    @PreAuthorize("@permissionService.hasPermission('content:article:writer')")
    @PostMapping
    @SystemLog("后台写文章")
    public ResponseResult add(@RequestBody AdminAddArticleDTO adminAddArticleDTO) {
        return articleService.add(adminAddArticleDTO);
    }
    
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable List<Long> id) {
        return articleService.deleteArticle(id);
    }
}