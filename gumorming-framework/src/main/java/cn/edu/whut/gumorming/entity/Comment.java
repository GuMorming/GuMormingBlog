package cn.edu.whut.gumorming.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 评论表(Comment)表实体类
 *
 * @author gumorming
 * @since 2023-07-07 19:44:01
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("comment")
public class Comment {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 类型 (1文章 2友链 3说说)
     */
    private Integer commentType;
    
    /**
     * 类型id
     */
    private Long typeId;
    
    /**
     * 父评论id
     */
    private Long parentId;
    
    /**
     * 回复评论id
     */
    private Long replyId;
    
    /**
     * 评论内容
     */
    private String commentContent;
    
    /**
     * 评论用户id
     */
    private Long fromUid;
    
    /**
     * 回复用户id
     */
    private Long toUid;
    
    /**
     * 是否通过 (0否 1是)
     */
    private Integer isCheck;
    
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    @TableLogic
    private Integer isDelete;
    
    
}