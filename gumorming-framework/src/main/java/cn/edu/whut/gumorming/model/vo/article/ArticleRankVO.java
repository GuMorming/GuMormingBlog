package cn.edu.whut.gumorming.model.vo.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 文章浏览量排行
 */
@Data
@Builder
@Schema(description = "文章浏览量排行")
public class ArticleRankVO {
    
    /**
     * 标题
     */
    @Schema(title = "标题")
    private String articleTitle;
    
    /**
     * 浏览量
     */
    @Schema(title = "浏览量")
    private Integer viewCount;
    
}