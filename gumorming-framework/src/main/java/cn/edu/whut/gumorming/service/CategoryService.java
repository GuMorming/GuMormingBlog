package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 分类表(Category)表服务接口
 *
 * @author gumorming
 * @since 2023-07-06 22:09:42
 */
public interface CategoryService extends IService<Category> {
    
    ResponseResult getCategoryList();
}