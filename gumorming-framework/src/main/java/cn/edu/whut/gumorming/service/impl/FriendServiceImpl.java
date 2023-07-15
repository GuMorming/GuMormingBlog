package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.entity.Friend;
import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import cn.edu.whut.gumorming.mapper.FriendMapper;
import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.vo.friend.AdminFriendVO;
import cn.edu.whut.gumorming.model.vo.friend.FriendVO;
import cn.edu.whut.gumorming.model.vo.response.PageVo;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.FriendService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * (Friend)表服务实现类
 *
 * @author gumorming
 * @since 2023-07-12 15:45:32
 */
@Service("friendService")
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {
    
    @Override
    public ResponseResult<List<FriendVO>> getFriendVOList() {
        List<FriendVO> friendVOS = BeanCopyUtils.copyBeanList(list(), FriendVO.class);
        
        return ResponseResult.okResult(friendVOS);
    }
    
    @Override
    public ResponseResult<PageVo> pageFriendVOList(GetParamsDTO getParamsDTO) {
        LambdaQueryWrapper<Friend> queryWrapper = new LambdaQueryWrapper<>();
        // 查询条件
        queryWrapper
                .like(StringUtils.hasText(getParamsDTO.getUrl()), Friend::getUrl, getParamsDTO.getUrl())
                .like(StringUtils.hasText(getParamsDTO.getFriendName()), Friend::getName, getParamsDTO.getFriendName())
                .orderByDesc(Friend::getCreateTime);
        // 分页
        Page<Friend> page = new Page<>(getParamsDTO.getCurrent(), getParamsDTO.getSize());
        page(page, queryWrapper);
        // 封装VO
        List<AdminFriendVO> adminFriendVOS = BeanCopyUtils.copyBeanList(page.getRecords(), AdminFriendVO.class);
        
        return ResponseResult.okResult(new PageVo<>(adminFriendVOS, page.getTotal()));
        
    }
    
    @Override
    public ResponseResult getFriendById(Long id) {
        Friend friend = getById(id);
        AdminFriendVO adminFriendVO = BeanCopyUtils.copyBean(friend, AdminFriendVO.class);
        
        return ResponseResult.okResult(adminFriendVO);
    }
    
    @Override
    public ResponseResult addFriend(FriendVO friendVO) {
        if (!StringUtils.hasText(friendVO.getName())) {
            return ResponseResult.errorResult(HttpCodeEnum.FRIEND_NAME_NULL);
        } else if (!StringUtils.hasText(friendVO.getUrl())) {
            return ResponseResult.errorResult(HttpCodeEnum.FRIEND_URL_NULL);
        }
        
        Friend friend = BeanCopyUtils.copyBean(friendVO, Friend.class);
        
        save(friend);
        
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult updateFriend(FriendVO friendVO) {
        if (!StringUtils.hasText(friendVO.getName())) {
            return ResponseResult.errorResult(HttpCodeEnum.FRIEND_NAME_NULL);
        } else if (!StringUtils.hasText(friendVO.getUrl())) {
            return ResponseResult.errorResult(HttpCodeEnum.FRIEND_URL_NULL);
        }
        
        Friend friend = BeanCopyUtils.copyBean(friendVO, Friend.class);
        
        updateById(friend);
        
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult deleteFriends(List<Long> ids) {
        for (Long id : ids) {
            removeById(id);
        }
        return ResponseResult.okResult();
    }
}