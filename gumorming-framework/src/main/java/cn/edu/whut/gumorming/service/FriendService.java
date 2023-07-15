package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.Friend;
import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.vo.PageVo;
import cn.edu.whut.gumorming.model.vo.friend.FriendVO;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * (Friend)表服务接口
 *
 * @author gumorming
 * @since 2023-07-12 15:45:32
 */
public interface FriendService extends IService<Friend> {
    
    ResponseResult<List<FriendVO>> getFriendVOList();
    
    ResponseResult<PageVo> pageFriendVOList(GetParamsDTO getParamsDTO);
    
    ResponseResult getFriendById(Long id);
    
    ResponseResult addFriend(FriendVO friendVO);
    
    ResponseResult updateFriend(FriendVO friendVO);
    
    ResponseResult deleteFriends(List<Long> id);
}