package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.vo.PageVo;
import cn.edu.whut.gumorming.model.vo.article.ArticleCardVO;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog-Back
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/12 16:02
 * @Email : gumorming@163.com
 * @Description :
 */
@RestController
@RequestMapping("/blog/timeline")
public class TimelineController {
    @Autowired
    private ArticleService articleService;
    
    @GetMapping("/list")
    public ResponseResult<PageVo<ArticleCardVO>> getTimelineList(GetParamsDTO getParamsDTO) {
        return articleService.getTimelineList(getParamsDTO);
    }
}