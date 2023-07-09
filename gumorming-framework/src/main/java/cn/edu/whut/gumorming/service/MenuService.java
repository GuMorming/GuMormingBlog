package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.domain.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author gumorming
 * @since 2023-07-09 19:04:49
 */
public interface MenuService extends IService<Menu> {
    
    List<String> selectByUserId(Long id);
    
    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}