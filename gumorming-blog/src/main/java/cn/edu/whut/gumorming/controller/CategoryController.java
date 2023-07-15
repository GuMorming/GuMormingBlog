package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.vo.PageVo;
import cn.edu.whut.gumorming.model.vo.article.ArticleCardVO;
import cn.edu.whut.gumorming.model.vo.category.CategoryVO;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/6 22:15
 * @Email : gumorming@163.com
 * @Description :
 */
@RestController
@RequestMapping("/blog/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/list")
    public ResponseResult<List<CategoryVO>> getCategoryVOList() {
        return categoryService.getCategoryVOList();
    }
    
    @GetMapping("/article")
    public ResponseResult<PageVo<ArticleCardVO>> getCategoryList(GetParamsDTO getParamsDTO) {
        return categoryService.getCategoryArticleList(getParamsDTO);
    }
}