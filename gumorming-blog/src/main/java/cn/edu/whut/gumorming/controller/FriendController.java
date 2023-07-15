package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.model.vo.friend.FriendVO;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog-Back
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/12 15:49
 * @Email : gumorming@163.com
 * @Description :
 */
@RestController
@RequestMapping("/blog/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;
    
    @GetMapping("/list")
    public ResponseResult<List<FriendVO>> getFriendVOList() {
        return friendService.getFriendVOList();
    }
}