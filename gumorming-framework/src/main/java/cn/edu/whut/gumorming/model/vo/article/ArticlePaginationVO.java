package cn.edu.whut.gumorming.model.vo.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文章上下篇
 */
@Data
@Schema(description = "文章上下篇")
public class ArticlePaginationVO {
    
    /**
     * 文章id
     */
    @Schema(title = "文章id")
    private Long id;
    
    /**
     * 文章缩略图
     */
    @Schema(title = "文章缩略图")
    private String articleCover;
    
    /**
     * 文章标题
     */
    @Schema(title = "文章标题")
    private String articleTitle;
}