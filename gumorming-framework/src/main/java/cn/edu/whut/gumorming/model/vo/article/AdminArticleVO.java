package cn.edu.whut.gumorming.model.vo.article;

import cn.edu.whut.gumorming.model.vo.tag.TagOptionVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 文章后台VO
 */
@Data
@Schema(description = "文章后台VO")
public class AdminArticleVO {
    
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
     * 文章摘要
     */
    @Schema(title = "文章摘要")
    private String articleSummary;
    
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
    @Schema(title = "是否允许评论 (0否 1是)")
    private Integer isComment;
    
    /**
     * 是否删除 (0否 1是)
     */
    @Schema(title = "是否删除 (0否 1是)")
    private Integer isDelete;
    
    /**
     * 状态 (1公开 2私密 3草稿)
     */
    @Schema(title = "状态 (1公开 2私密 3草稿)")
    private Integer status;
    
    /**
     * 点赞量
     */
    @Schema(title = "点赞量")
    private Integer likeCount;
    
    /**
     * 浏览量
     */
    @Schema(title = "浏览量")
    private Integer viewCount;
    
    /**
     * 文章分类名
     */
    @Schema(title = "文章分类名")
    private String categoryName;
    
    /**
     * 文章标签
     */
    @Schema(title = "文章标签")
    private List<TagOptionVO> tagVOList;
    
    /**
     * 发表时间
     */
    @Schema(title = "发表时间")
    private Date createTime;
    
}