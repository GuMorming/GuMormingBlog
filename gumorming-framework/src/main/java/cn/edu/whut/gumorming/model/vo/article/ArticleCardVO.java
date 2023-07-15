package cn.edu.whut.gumorming.model.vo.article;

import cn.edu.whut.gumorming.model.vo.category.CategoryOptionVO;
import cn.edu.whut.gumorming.model.vo.tag.TagOptionVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "文章卡片VO")
public class ArticleCardVO {
    
    /**
     * 文章id
     */
    @Schema(title = "文章id")
    private Long id;
    
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
    
    @Schema(title = "文章摘要")
    private String articleSummary;
    
    @Schema(title = "文章分类")
    private CategoryOptionVO category;
    
    /**
     * 文章标签
     */
    @Schema(title = "文章标签")
    private List<TagOptionVO> tagVOList;
    
    /**
     * 是否置顶 (0否 1是)
     */
    @Schema(title = "是否置顶 (0否 1是)")
    private Integer isTop;
    
    /**
     * 发表时间
     */
    @Schema(title = "发表时间")
    private Date createTime;
}