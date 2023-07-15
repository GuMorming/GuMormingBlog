package cn.edu.whut.gumorming.model.vo.tag;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 标签VO
 **/
@Data
@Schema(description = "标签VO")
public class TagVO {
    
    /**
     * 标签id
     */
    @Schema(title = "标签id")
    private Long id;
    
    /**
     * 标签名
     */
    @Schema(title = "标签名")
    private String tagName;
    
    /**
     * 文章数量
     */
    @Schema(title = "文章数量")
    private Integer articleCount;
}