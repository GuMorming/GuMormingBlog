package cn.edu.whut.gumorming.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 文章表(Article)表实体类
 *
 * @author gumorming
 * @since 2023-07-06 15:48:16
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("article")
public class Article {
    //文章id@TableId
    @TableId(type = IdType.AUTO)
    private Long id;
    
    //作者id
    private Long userId;
    //分类id
    private Long categoryId;
    //缩略图
    private String articleCover;
    // 文章概述
    private String articleSummary;
    //文章标题
    private String articleTitle;
    //文章内容
    private String articleContent;
    //类型 (1原创 2转载 3翻译)
    private Integer articleType;
    // 浏览量
    private Long viewCount;
    //是否置顶 (0否 1是）
    private Integer isTop;
    //是否删除 (0否 1是)
    @TableLogic
    private Integer isDelete;
    //是否允许评论 (0否 1是)
    private Integer isComment;
    //状态 (1公开 2私密 3草稿)
    private Integer status;
    //发表时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    
    public Article(Long id, Long viewCount) {
        this.id = id;
        this.viewCount = viewCount;
    }
}