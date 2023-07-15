package cn.edu.whut.gumorming.model.dto.article;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.domain.dto
 * @createTime : 2023/7/10 14:47
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "写文章DTO")
public class AdminAddArticleDTO {
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
    @NotBlank(message = "文章标题不能为空")
    @Schema(title = "文章标题")
    private String articleTitle;
    
    /**
     * 文章内容
     */
    @NotBlank(message = "文章内容不能为空")
    @Schema(title = "文章内容")
    private String articleContent;
    
    
    @Schema(title = "文章摘要")
    private String articleSummary;
    
    /**
     * 文章类型 (1原创 2转载 3翻译)
     */
    @Schema(title = "文章类型 (1原创 2转载 3翻译)")
    private Integer articleType;
    
    /**
     * 分类名
     */
    @NotBlank(message = "文章分类不能为空")
    @Schema(title = "分类id")
    private Long categoryId;
    
    /**
     * 标签名
     */
    @Schema(title = "标签名")
    private List<Long> tagIds;
    
    /**
     * 是否置顶 (0否 1是)
     */
    @Schema(title = "是否置顶 (0否 1是)")
    private Integer isTop;
    
    /**
     * 是否推荐 (0否 1是)
     */
    @Schema(title = "是否允许评论 (0否 1是)")
    private Integer isComment;
    
    /**
     * 状态 (1公开 2私密 3草稿)
     */
    @Schema(title = "状态 (1公开 2私密 3草稿)")
    private Integer status;
    
}