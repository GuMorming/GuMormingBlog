package cn.edu.whut.gumorming.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 菜单权限表(Menu)表实体类
 *
 * @author gumorming
 * @since 2023-07-09 19:04:49
 */
@SuppressWarnings("serial")
@Data
@Accessors(chain = true) // 链式编程, setter 返回值为自身
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_menu")
public class Menu {
    //菜单ID@TableId
    @TableId(type = IdType.AUTO)
    private Long id;
    
    //菜单名称
    private String menuName;
    //父菜单ID
    
    private Long parentId;
    //显示顺序
    private Integer orderNum;
    //路由地址
    private String path;
    //组件路径
    private String component;
    //是否为外链（0是 1否）
    private Integer isFrame;
    //菜单类型（M目录 C菜单 F按钮）
    private String menuType;
    //菜单状态（0显示 1隐藏）
    private String visible;
    //菜单状态（0正常 1停用）
    private Integer isDisable;
    //权限标识
    private String perms;
    //菜单图标
    private String icon;
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //备注
    private String remark;
    
    private Integer isDelete;
    @TableField(exist = false)
    private List<Menu> children;
    
    
}