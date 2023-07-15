package cn.edu.whut.gumorming.model.vo.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 分类列表
 **/
@Data
@Schema(description = "分类列表")
public class CategoryVO {
    
    /**
     * 分类id
     */
    @Schema(title = "分类id")
    private Long id;
    
    /**
     * 分类名
     */
    @Schema(title = "分类名")
    private String categoryName;
    
    /**
     * 文章数量
     */
    @Schema(title = "文章数量")
    private Long articleCount;
}