package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.service.impl
 * @createTime : 2023/7/10 19:08
 * @Email : gumorming@163.com
 * @Description :
 */
@Service("permissionService")
public class PermissionServiceImpl {
    /**
     * 判断当前用户是否具有permission
     *
     * @param permission 要判断的权限
     * @return
     */
    public boolean hasPermission(String permission) {
        // 管理员, 直接返回true
        if (SecurityUtils.isAdmin()) {
            return true;
        }
        // 其他用户, 查询相应权限并判断
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        return permissions.contains(permission);
    }
}