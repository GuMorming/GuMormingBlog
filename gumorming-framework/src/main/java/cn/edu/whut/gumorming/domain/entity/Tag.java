package cn.edu.whut.gumorming.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 标签(Tag)表实体类
 *
 * @author gumorming
 * @since 2023-07-09 15:55:49
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tag")
public class Tag {
    @TableId
    private Long id;
    
    //标签名
    private String name;
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;
    //备注
    private String remark;
    
    
}