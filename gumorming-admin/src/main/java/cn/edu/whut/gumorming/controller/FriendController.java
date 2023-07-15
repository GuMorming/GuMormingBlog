package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.vo.PageVo;
import cn.edu.whut.gumorming.model.vo.friend.FriendVO;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/9 21:55
 * @Email : gumorming@163.com
 * @Description :
 */
@RestController
@RequestMapping("/content/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;
    
    @GetMapping("/list")
    public ResponseResult<PageVo> pageFriendVOList(GetParamsDTO getParamsDTO) {
        return friendService.pageFriendVOList(getParamsDTO);
    }
    
    @GetMapping("/{id}")
    public ResponseResult getFriend(@PathVariable Long id) {
        return friendService.getFriendById(id);
    }
    
    
    @PostMapping
    public ResponseResult addLink(@RequestBody FriendVO friendVO) {
        return friendService.addFriend(friendVO);
    }
    
    @PutMapping
    public ResponseResult updateLink(@RequestBody FriendVO friendVO) {
        return friendService.updateFriend(friendVO);
    }
    
    @DeleteMapping("/{id}")
    public ResponseResult deleteFriends(@PathVariable List<Long> id) {
        return friendService.deleteFriends(id);
    }
    
}