package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.ArticleConstants;
import cn.edu.whut.gumorming.constants.SystemConstants;
import cn.edu.whut.gumorming.entity.Article;
import cn.edu.whut.gumorming.entity.Category;
import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import cn.edu.whut.gumorming.mapper.CategoryMapper;
import cn.edu.whut.gumorming.model.dto.category.AdminUpdateCategoryDTO;
import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.vo.article.ArticleCardVO;
import cn.edu.whut.gumorming.model.vo.category.CategoryVO;
import cn.edu.whut.gumorming.model.vo.category.ExcelCategoryVO;
import cn.edu.whut.gumorming.model.vo.response.PageVo;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.ArticleService;
import cn.edu.whut.gumorming.service.ArticleTagService;
import cn.edu.whut.gumorming.service.CategoryService;
import cn.edu.whut.gumorming.service.TagService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import cn.edu.whut.gumorming.utils.WebUtils;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * 分类表(Category)表服务实现类
 *
 * @author gumorming
 * @since 2023-07-06 22:09:43
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private TagService tagService;
    
    /**
     * 返回特定分类下的文章列表
     *
     * @return
     */
    @Override
    public ResponseResult<PageVo<ArticleCardVO>> getCategoryArticleList(GetParamsDTO getParamsDTO) {
        LambdaQueryWrapper<Article> articleQueryWrapper = new LambdaQueryWrapper<>();
        // 1. 查询条件
        articleQueryWrapper
                // 根据分类id查询文章
                .eq(Article::getCategoryId, getParamsDTO.getCategoryId())
                // 公开状态
                .eq(Article::getStatus, ArticleConstants.STATUS_PUBLIC)
                // 置顶文章在前
                .orderByDesc(Article::getIsTop);
        
        // 2.分页
        Page<Article> page = new Page<>(getParamsDTO.getCurrent(), getParamsDTO.getSize());
        articleService.page(page, articleQueryWrapper);
        List<Article> articles = page.getRecords();
        String categoryName = getById(getParamsDTO.getCategoryId()).getCategoryName();
        // 封装首页文章VO
        List<ArticleCardVO> articleCardVOS = BeanCopyUtils.copyBeanList(articles, ArticleCardVO.class);
        articleCardVOS = articleService.setCategoryAndTags(articleCardVOS);
        
        return ResponseResult.okResult(new PageVo<>(articleCardVOS, page.getTotal()));
    }
    
    @Override
    public ResponseResult getCategoryList() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        // 查询所有正常状态的分类
        queryWrapper.eq(Category::getIsDisable, SystemConstants.FALSE);
        List<Category> categories = list(queryWrapper);
        
        // 封装VO
        List<CategoryVO> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVO.class);
        
        return ResponseResult.okResult(categoryVos);
    }
    
    @Override
    public void export2Excel(HttpServletResponse response) throws IOException {
        try {
            //设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx", response);
            //获取需要导出的数据
            List<Category> categories = list();
            
            List<ExcelCategoryVO> excelCategoryVOS = BeanCopyUtils.copyBeanList(categories, ExcelCategoryVO.class);
            //把数据写入到Excel中
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVO.class).autoCloseStream(Boolean.FALSE).sheet("分类导出")
                    .doWrite(excelCategoryVOS);
            
        } catch (Exception e) {
            //如果出现异常也要响应json
            ResponseResult result = ResponseResult.errorResult(HttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }
    
    /**
     * 返回所有分类
     */
    @Override
    public ResponseResult<PageVo> pageCategoryList(GetParamsDTO getParamsDTO) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        // 查询条件
        queryWrapper
                // 是否禁用
                .eq(Objects.nonNull(getParamsDTO.getIsDisable()), Category::getIsDisable, getParamsDTO.getIsDisable())
                // 分类名
                .like(StringUtils.hasText(getParamsDTO.getCategoryName()), Category::getCategoryName, getParamsDTO.getCategoryName())
                // 未删除
                .eq(Category::getIsDelete, SystemConstants.FALSE);
        
        // 分页
        Page<Category> page = new Page<>(getParamsDTO.getCurrent(), getParamsDTO.getSize());
        page(page, queryWrapper);
        // 封装VO
//        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(page.getRecords(), CategoryVo.class);
        PageVo<Category> pageVo = new PageVo<>(page.getRecords(), page.getTotal());
        
        return ResponseResult.okResult(pageVo);
    }
    
    @Override
    public ResponseResult addCategory(Category category) {
        if (!StringUtils.hasText(category.getCategoryName())) {
            return ResponseResult.errorResult(HttpCodeEnum.CATEGORY_NAME_NULL);
        }
        save(category);
        
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult getCategoryById(Long id) {
        Category category = getById(id);
        // todo 封装vo
        return ResponseResult.okResult(category);
    }
    
    @Override
    public ResponseResult udpateCategory(Category category) {
        if (!StringUtils.hasText(category.getCategoryName())) {
            return ResponseResult.errorResult(HttpCodeEnum.CATEGORY_NAME_NULL);
        }
        
        updateById(category);
        
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult deleteCategories(List<Long> id) {
        for (Long categoryId : id) {
            removeById(categoryId);
        }
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult<List<CategoryVO>> getCategoryVOList() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        // 获取所有生效的分类
        queryWrapper.eq(Category::getIsDelete, Boolean.FALSE);
        List<CategoryVO> categoryVOs = BeanCopyUtils.copyBeanList(list(queryWrapper), CategoryVO.class);
        // 获取各分类下文章数量
        for (CategoryVO categoryVO : categoryVOs) {
            categoryVO.setArticleCount(articleService.count(new LambdaQueryWrapper<Article>().eq(Article::getCategoryId, categoryVO.getId())));
        }
        
        return ResponseResult.okResult(categoryVOs);
    }
    
    @Override
    public ResponseResult updateCategoryStatusById(AdminUpdateCategoryDTO adminUpdateCategoryDTO) {
        Category category = BeanCopyUtils.copyBean(adminUpdateCategoryDTO, Category.class);
        
        update(category, new LambdaUpdateWrapper<Category>()
                .eq(Category::getId, category.getId())
                .set(Category::getIsDisable, category.getIsDisable())
        );
        
        return ResponseResult.okResult();
    }
}