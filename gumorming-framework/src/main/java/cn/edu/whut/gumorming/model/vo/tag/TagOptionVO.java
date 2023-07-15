package cn.edu.whut.gumorming.model.vo.tag;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签选项VO
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "标签选项VO")
public class TagOptionVO {
    
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
}