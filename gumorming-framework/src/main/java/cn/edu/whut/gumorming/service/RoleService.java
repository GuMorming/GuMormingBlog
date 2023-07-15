package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.Role;
import cn.edu.whut.gumorming.model.dto.AddRoleDto;
import cn.edu.whut.gumorming.model.dto.QueryRoleDto;
import cn.edu.whut.gumorming.model.vo.PageVo;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author gumorming
 * @since 2023-07-09 19:10:43
 */
public interface RoleService extends IService<Role> {
    
    List<String> selectRoleKeyByUserId(Long id);
    
    ResponseResult<PageVo> listPageRole(Integer pageNum, Integer pageSize, QueryRoleDto queryRoleDto);
    
    ResponseResult changeStatus(QueryRoleDto queryRoleDto);
    
    ResponseResult addRole(AddRoleDto addRoleDto);
    
    ResponseResult getRoleById(Long id);
    
    ResponseResult updateRoleById(AddRoleDto addRoleDto);
    
    ResponseResult deleteRole(List<Long> id);
    
    ResponseResult listAllNormalStatusRole();
}