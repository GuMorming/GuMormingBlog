package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.annotation.SystemLog;
import cn.edu.whut.gumorming.entity.Category;
import cn.edu.whut.gumorming.model.dto.category.AdminUpdateCategoryDTO;
import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.vo.response.PageVo;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.CategoryService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/10 14:11
 * @Email : gumorming@163.com
 * @Description :
 */
@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    
    @GetMapping("/list")
    @SystemLog("后台获取分类列表")
    public ResponseResult<PageVo> getPageCategoryList(GetParamsDTO getParamsDTO) {
        return categoryService.pageCategoryList(getParamsDTO);
    }
    
    @GetMapping("/{id}")
    public ResponseResult getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
    
    
    @GetMapping("/listAllCategory")
    @SystemLog("后台写文章时获取所有分类")
    public ResponseResult listAllCategory() {
        return categoryService.getCategoryList();
    }
    
    @GetMapping("/export")
    @SystemLog("后台导出分类Excel")
    @PreAuthorize("@permissionService.hasPermission('content:category:export')")
    public void exportCategory2Excel(HttpServletResponse response) throws IOException {
        categoryService.export2Excel(response);
    }
    
    @PostMapping
    public ResponseResult addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }
    
    @PutMapping
    public ResponseResult updateCategory(@RequestBody Category category) {
        return categoryService.udpateCategory(category);
    }
    
    @PutMapping("/changeStatus")
    public ResponseResult changeCategoryStatus(@RequestBody AdminUpdateCategoryDTO adminUpdateCategoryDTO) {
        return categoryService.updateCategoryStatusById(adminUpdateCategoryDTO);
    }
    
    @DeleteMapping("/{id}")
    public ResponseResult deleteCategories(@PathVariable List<Long> id) {
        return categoryService.deleteCategories(id);
    }
    
}