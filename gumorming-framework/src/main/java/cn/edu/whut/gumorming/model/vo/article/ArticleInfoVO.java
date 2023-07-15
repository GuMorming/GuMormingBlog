package cn.edu.whut.gumorming.model.vo.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 文章信息
 */
@Data
@Schema(description = "文章信息")
public class ArticleInfoVO {
    
    /**
     * 文章id
     */
    @Schema(title = "文章id")
    private Long id;
    
    /**
     * 分类id
     */
    @JsonIgnore
    @Schema(title = "分类id")
    private Long categoryId;
    
    /**
     * 文章缩略图
     */
    @Schema(title = "文章缩略图")
    private String articleCover;
    
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
     * 文章类型 (1原创 2转载 3翻译)
     */
    @Schema(title = "文章类型 (1原创 2转载 3翻译)")
    private Integer articleType;
    
    /**
     * 是否置顶 (0否 1是)
     */
    @Schema(title = "是否置顶 (0否 1是)")
    private Integer isTop;
    
    /**
     * 是否推荐 (0否 1是)
     */
    @Schema(title = "是否推荐 (0否 1是)")
    private Integer isRecommend;
    
    /**
     * 状态 (1公开 2私密 3草稿)
     */
    @Schema(title = "状态 (1公开 2私密 3草稿)")
    private Integer status;
    
    /**
     * 文章分类名
     */
    @Schema(title = "文章分类名")
    private String categoryName;
    
    /**
     * 文章标签名
     */
    @Schema(title = "文章标签名")
    private List<String> tagNameList;
}