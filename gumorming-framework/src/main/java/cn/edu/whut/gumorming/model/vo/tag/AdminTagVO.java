package cn.edu.whut.gumorming.model.vo.tag;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 标签后台VO
 **/
@Data
@Schema(description = "标签后台VO")
public class AdminTagVO {
    
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
    
    /**
     * 创建时间
     */
    @Schema(title = "创建时间")
    private Date createTime;
    
    
    @Schema(title = "备注")
    private String remark;
    
}