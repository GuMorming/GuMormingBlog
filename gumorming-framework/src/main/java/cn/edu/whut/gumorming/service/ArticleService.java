package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.Article;
import cn.edu.whut.gumorming.model.dto.QueryArticleDto;
import cn.edu.whut.gumorming.model.dto.article.AdminAddArticleDTO;
import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.vo.PageVo;
import cn.edu.whut.gumorming.model.vo.article.ArticleCardVO;
import cn.edu.whut.gumorming.model.vo.article.ArticleSearchVO;
import cn.edu.whut.gumorming.model.vo.article.BlogArticleVO;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.service
 * @createTime : 2023/7/6 15:55
 * @Email : gumorming@163.com
 * @Description :
 */

public interface ArticleService extends IService<Article> {
    ResponseResult topArticleList();
    
    ResponseResult<PageVo> homePageArticleVO(Integer pageNum, Integer pageSize);
    
    ResponseResult<BlogArticleVO> getArticleDetail(Long id);
    
    ResponseResult updateArticleViewCount(Long id);
    
    ResponseResult add(AdminAddArticleDTO adminAddArticleDTO);
    
    ResponseResult getAdminArticleList(Integer pageNum, Integer pageSize, QueryArticleDto queryArticleDto);
    
    ResponseResult getArticleById(Long id);
    
    ResponseResult updateArticleById(AdminAddArticleDTO adminAddArticleDTO);
    
    ResponseResult deleteArticle(List<Long> id);
    
    public List<ArticleCardVO> setCategoryAndTags(List<ArticleCardVO> articleCardVOS);
    
    ResponseResult<PageVo<ArticleCardVO>> getTimelineList(GetParamsDTO getParamsDTO);
    
    ResponseResult<List<ArticleSearchVO>> searchPageArticleVO(String keyword);
}