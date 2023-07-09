package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

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
    
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);
    
    ResponseResult getArticleDetail(Long id);
    
    ResponseResult updateArticleViewCount(Long id);
}