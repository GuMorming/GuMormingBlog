package cn.edu.whut.gumorming.controller;

import cn.edu.whut.gumorming.entity.Menu;
import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.controller
 * @createTime : 2023/7/11 8:34
 * @Email : gumorming@163.com
 * @Description :
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {
    @Autowired
    MenuService menuService;
    
    @GetMapping("/list")
    public ResponseResult list(GetParamsDTO getParamsDTO) {
        return menuService.listAllMenu(getParamsDTO);
    }
    
    @GetMapping("/{id}")
    public ResponseResult getMenuById(@PathVariable Long id) {
        return menuService.getMenuById(id);
    }
    
    /**
     * 获取菜单树
     *
     * @return
     */
    @GetMapping("/treeselect")
    public ResponseResult treeselect() {
        return menuService.treeselect();
    }
    
    /**
     * 获取对应角色的菜单树
     *
     * @param id
     * @return
     */
    @GetMapping("/roleMenuTreeselect/{id}")
    public ResponseResult roleMenuTreeselect(@PathVariable Long id) {
        return menuService.roleMenuTreeselect(id);
    }
    
    @PutMapping
    public ResponseResult updateMenu(@RequestBody Menu menu) {
        return menuService.updateMenu(menu);
    }
    
    @PostMapping
    public ResponseResult addMenu(@RequestBody Menu menu) {
        return menuService.addMenu(menu);
    }
    
    @DeleteMapping("/{menuId}")
    public ResponseResult deleteMenuById(@PathVariable Long menuId) {
        return menuService.deleteMenuByid(menuId);
    }
}