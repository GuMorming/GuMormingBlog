package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.annotation.SystemLog;
import cn.edu.whut.gumorming.entity.Tag;
import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.vo.response.PageVo;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @SystemLog("后台获取分页标签列表")
    public ResponseResult<PageVo> tagList(GetParamsDTO getParamsDTO) {
        return tagService.pageTagList(getParamsDTO);
    }
    
    /**
     * 根据tag id获取信息
     *
     * @param id pathVariable
     * @return
     */
    @GetMapping("/{id}")
    @SystemLog("后台根据id获取标签信息")
    public ResponseResult getTagInfoById(@PathVariable Long id) {
        return tagService.getTagInfoById(id);
    }
    
    @GetMapping("/listAllTag")
    @SystemLog("后台获取标签列表")
    public ResponseResult getTagList() {
        return tagService.getTagList();
    }
    
    @PutMapping
    @SystemLog("后台修改标签")
    public ResponseResult updateTagInfoById(@RequestBody Tag tag) {
        return tagService.updateTagInfoById(tag);
    }
    
    @PostMapping
    @SystemLog("后台添加标签")
    public ResponseResult addTag(@RequestBody Tag tag) {
        return tagService.addTag(tag);
    }
    
    
    @DeleteMapping("/{id}")
    @SystemLog("后台删除标签")
    public ResponseResult deleteTag(@PathVariable("id") List<Long> id) {
        return tagService.deleteTag(id);
    }
    
}