package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.annotation.SystemLog;
import cn.edu.whut.gumorming.model.dto.AddRoleDto;
import cn.edu.whut.gumorming.model.dto.QueryRoleDto;
import cn.edu.whut.gumorming.model.vo.PageVo;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/11 9:12
 * @Email : gumorming@163.com
 * @Description :
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    
    
    @GetMapping("/list")
    public ResponseResult<PageVo> pageListRole(Integer pageNum, Integer pageSize, QueryRoleDto queryRoleDto) {
        return roleService.listPageRole(pageNum, pageSize, queryRoleDto);
    }
    
    /**
     * 所有正常状态的角色
     *
     * @return
     */
    @GetMapping("/listAllRole")
    public ResponseResult listAllRole() {
        return roleService.listAllNormalStatusRole();
    }
    
    @GetMapping("/{id}")
    public ResponseResult getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }
    
    @PostMapping
    public ResponseResult addRole(@RequestBody AddRoleDto addRoleDto) {
        return roleService.addRole(addRoleDto);
    }
    
    @PutMapping("/changeStatus")
    public ResponseResult changeRoleStatus(@RequestBody QueryRoleDto queryRoleDto) {
        return roleService.changeStatus(queryRoleDto);
    }
    
    @PutMapping
    public ResponseResult updateRoleById(@RequestBody AddRoleDto addRoleDto) {
        return roleService.updateRoleById(addRoleDto);
    }
    
    @DeleteMapping("/{id}")
    @SystemLog("后台删除角色")
    public ResponseResult deleteRole(@PathVariable("id") List<Long> id) {
        return roleService.deleteRole(id);
    }
    
}