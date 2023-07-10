package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.annotation.SystemLog;
import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.dto.TagListDto;
import cn.edu.whut.gumorming.domain.entity.Tag;
import cn.edu.whut.gumorming.domain.vo.PageVo;
import cn.edu.whut.gumorming.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/9 16:00
 * @Email : gumorming@163.com
 * @Description :
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;
    
    /**
     * 查询标签列表
     * query参数
     *
     * @return
     */
    @GetMapping("/list")
    @SystemLog("后台获取标签列表")
    public ResponseResult<PageVo> tagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        return tagService.pageTagList(pageNum, pageSize, tagListDto);
    }
    
    @PostMapping("/addTag")
    public ResponseResult addTag(@RequestBody Tag tag) {
        return tagService.addTag(tag);
    }
}