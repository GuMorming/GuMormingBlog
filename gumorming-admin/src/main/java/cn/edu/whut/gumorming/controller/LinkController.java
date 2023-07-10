package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.vo.PageVo;
import cn.edu.whut.gumorming.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/9 21:55
 * @Email : gumorming@163.com
 * @Description :
 */
@RestController
@RequestMapping("/content/link")
public class LinkController {
    @Autowired
    private LinkService linkService;
    
    @GetMapping("/list")
    public ResponseResult<PageVo> linkList(Integer pageNum, Integer pageSize) {
        return linkService.pageLinkList(pageNum, pageSize);
    }
}