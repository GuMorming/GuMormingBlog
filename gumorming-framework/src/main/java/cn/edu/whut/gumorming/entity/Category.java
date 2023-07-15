package cn.edu.whut.gumorming.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 分类表(Category)表实体类
 *
 * @author gumorming
 * @since 2023-07-06 22:09:41
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("category")
public class Category {
    /**
     * 评论id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 分类名
     */
    private String categoryName;
    
    /**
     * 状态
     */
    private Integer isDisable;
    
    /**
     * 评论时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    @TableLogic
    private Integer isDelete;
    
    
}