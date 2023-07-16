package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.Tag;
import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.vo.article.ArticleCardVO;
import cn.edu.whut.gumorming.model.vo.response.PageVo;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.model.vo.tag.TagVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 标签(Tag)表服务接口
 *
 * @author gumorming
 * @since 2023-07-09 15:55:49
 */
public interface TagService extends IService<Tag> {
    
    ResponseResult<PageVo> pageTagList(GetParamsDTO getParamsDTO);
    
    ResponseResult addTag(Tag tag);
    
    ResponseResult deleteTag(List<Long> id);
    
    ResponseResult getTagInfoById(Long id);
    
    ResponseResult updateTagInfoById(Tag tag);
    
    ResponseResult getTagList();
    
    ResponseResult<PageVo<ArticleCardVO>> getTagArticleList(GetParamsDTO getParamsDTO);
    
    ResponseResult<PageVo<TagVO>> getPageTagList();
}