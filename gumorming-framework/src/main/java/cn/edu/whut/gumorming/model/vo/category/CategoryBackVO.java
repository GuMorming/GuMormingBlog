package cn.edu.whut.gumorming.model.vo.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 分类后台VO
 **/
@Data
@Schema(description = "分类后台VO")
public class CategoryBackVO {
    
    /**
     * 分类id
     */
    @Schema(title = "分类id")
    private Integer id;
    
    /**
     * 分类名
     */
    @Schema(title = "分类名")
    private String categoryName;
    
    /**
     * 文章数量
     */
    @Schema(title = "文章数量")
    private Integer articleCount;
    
    /**
     * 创建时间
     */
    @Schema(title = "创建时间")
    private LocalDateTime createTime;
    
}