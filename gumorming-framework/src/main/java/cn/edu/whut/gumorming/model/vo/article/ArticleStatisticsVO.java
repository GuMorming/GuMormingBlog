package cn.edu.whut.gumorming.model.vo.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文章贡献统计
 */
@Data
@Schema(description = "文章贡献统计")
public class ArticleStatisticsVO {
    
    /**
     * 日期
     */
    @Schema(title = "日期")
    private String date;
    
    /**
     * 数量
     */
    @Schema(title = "数量")
    private Integer count;
}