package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.User;
import cn.edu.whut.gumorming.model.dto.UserInfoDTO;
import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.dto.user.AdminAddUserDTO;
import cn.edu.whut.gumorming.model.dto.user.AdminUpdateUserDTO;
import cn.edu.whut.gumorming.model.vo.PageVo;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 用户表(User)表服务接口
 *
 * @author gumorming
 * @since 2023-07-07 13:49:37
 */
public interface UserService extends IService<User> {
    
    ResponseResult getUserInfo();
    
    ResponseResult updateUserInfo(UserInfoDTO userInfoDTO);
    
    ResponseResult register(User user);
    
    ResponseResult<PageVo> getPageUserList(GetParamsDTO getParamsDTO);
    
    ResponseResult addUser(AdminAddUserDTO adminAddUserDTO);
    
    ResponseResult deleteUser(List<Long> id);
    
    ResponseResult getUserById(Long id);
    
    ResponseResult updateUserById(AdminUpdateUserDTO updateUserDTO);
    
    ResponseResult updateUserStatusById(AdminUpdateUserDTO adminUpdateUserDTO);
}