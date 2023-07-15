package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.Category;
import cn.edu.whut.gumorming.model.dto.AdminUpdateCategoryDTO;
import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.vo.PageVo;
import cn.edu.whut.gumorming.model.vo.article.ArticleCardVO;
import cn.edu.whut.gumorming.model.vo.category.CategoryVO;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


/**
 * 分类表(Category)表服务接口
 *
 * @author gumorming
 * @since 2023-07-06 22:09:42
 */
public interface CategoryService extends IService<Category> {
    
    ResponseResult<PageVo<ArticleCardVO>> getCategoryArticleList(GetParamsDTO getParamsDTO);
    
    ResponseResult getCategoryList();
    
    void export2Excel(HttpServletResponse response) throws IOException;
    
    ResponseResult<PageVo> pageCategoryList(GetParamsDTO getParamsDTO);
    
    ResponseResult addCategory(Category category);
    
    ResponseResult getCategoryById(Long id);
    
    ResponseResult udpateCategory(Category category);
    
    ResponseResult deleteCategories(List<Long> id);
    
    ResponseResult<List<CategoryVO>> getCategoryVOList();
    
    ResponseResult updateCategoryStatusById(AdminUpdateCategoryDTO adminUpdateCategoryDTO);
}