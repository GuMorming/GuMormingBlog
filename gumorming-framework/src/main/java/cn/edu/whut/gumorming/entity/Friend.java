package cn.edu.whut.gumorming.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (Friend)表实体类
 *
 * @author gumorming
 * @since 2023-07-12 15:45:32
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("friend")
public class Friend {
    //友链id@TableId
    @TableId(type = IdType.AUTO)
    private Long id;
    
    //友链名称
    private String name;
    //友链颜色
    private String color;
    //友链头像
    private String avatar;
    //友链地址
    private String url;
    //友链介绍
    private String introduction;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    
    
}