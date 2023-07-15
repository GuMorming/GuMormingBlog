package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.vo.TagVo;
import cn.edu.whut.gumorming.model.vo.article.ArticleCardVO;
import cn.edu.whut.gumorming.model.vo.response.PageVo;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog-Back
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/12 14:53
 * @Email : gumorming@163.com
 * @Description :
 */
@RestController
@RequestMapping("/blog/tag")
public class TagController {
    @Autowired
    TagService tagService;
    
    @GetMapping("/list")
    public ResponseResult<List<TagVo>> getTagVOList() {
        return tagService.getTagList();
    }
    
    @GetMapping("/article")
    public ResponseResult<PageVo<ArticleCardVO>> getTagArticleList(GetParamsDTO getParamsDTO) {
        return tagService.getTagArticleList(getParamsDTO);
    }
}