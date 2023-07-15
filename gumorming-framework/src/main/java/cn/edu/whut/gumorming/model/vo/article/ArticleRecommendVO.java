package cn.edu.whut.gumorming.model.vo.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 推荐文章
 **/
@Data
@Schema(description = "推荐文章")
public class ArticleRecommendVO {
    
    /**
     * 文章id
     */
    @Schema(title = "文章id")
    private Long id;
    
    /**
     * 文章标题
     */
    @Schema(title = "文章标题")
    private String articleTitle;
    
    /**
     * 文章缩略图
     */
    @Schema(title = "文章缩略图")
    private String articleCover;
    
    /**
     * 发布时间
     */
    @Schema(title = "发布时间")
    private LocalDateTime createTime;
}