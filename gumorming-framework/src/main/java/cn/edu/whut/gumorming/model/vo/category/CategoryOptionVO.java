package cn.edu.whut.gumorming.model.vo.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 分类选项VO
 **/
@Data
@Schema(description = "分类选项VO")
public class CategoryOptionVO {
    
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
}