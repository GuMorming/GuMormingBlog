package cn.edu.whut.gumorming.model.vo.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章搜索VO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "文章搜索VO")
public class ArticleSearchVO {
    
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
     * 文章内容
     */
    @Schema(title = "文章内容")
    private String articleContent;
    
    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    private Integer isDelete;
    
    /**
     * 文章状态
     */
    @Schema(title = "文章状态")
    private Integer status;
}