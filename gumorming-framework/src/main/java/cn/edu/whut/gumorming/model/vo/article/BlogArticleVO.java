package cn.edu.whut.gumorming.model.vo.article;

import cn.edu.whut.gumorming.model.vo.category.CategoryOptionVO;
import cn.edu.whut.gumorming.model.vo.tag.TagOptionVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 文章VO
 */
@Data
@Schema(description = "文章VO")
public class BlogArticleVO {
    
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
    
    /**
     * 文章类型 (1原创 2转载 3翻译)
     */
    @Schema(title = "文章类型 (1原创 2转载 3翻译)")
    private Integer articleType;
    
    /**
     * 浏览量
     */
    @Schema(title = "浏览量")
    private Long viewCount;
    
    /**
     * 点赞量
     */
    @Schema(title = "点赞量")
    private Integer likeCount;
    
    /**
     * 文章分类
     */
    @Schema(title = "文章分类")
    private CategoryOptionVO category;
    
    /**
     * 文章标签
     */
    @Schema(title = "文章标签")
    private List<TagOptionVO> tagVOList;
    
    /**
     * 上一篇文章
     */
    @Schema(title = "上一篇文章")
    private ArticlePaginationVO lastArticle;
    
    /**
     * 下一篇文章
     */
    @Schema(title = "下一篇文章")
    private ArticlePaginationVO nextArticle;
    
    /**
     * 发表时间
     */
    @Schema(title = "发表时间")
    private Date createTime;
    
    /**
     * 更新时间
     */
    @Schema(title = "更新时间")
    private Date updateTime;
}