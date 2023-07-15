package cn.edu.whut.gumorming.model.vo.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 文章条件列表VO
 **/
@Data
@Builder
@Schema(description = "文章条件列表VO")
public class ArticleConditionList {
    
    /**
     * 文章列表
     */
    @Schema(title = "文章列表")
    private List<ArticleConditionVO> articleConditionVOList;
    
    /**
     * 条件名
     */
    @Schema(title = "条件名")
    private String name;
}