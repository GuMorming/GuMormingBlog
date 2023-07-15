package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author gumorming
 * @since 2023-07-09 19:04:49
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    
    List<String> selectPermsByUserId(Long id);
    
    List<Menu> selectAllRouterMenu();
    
    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}