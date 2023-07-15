package cn.edu.whut.gumorming.model.vo.article;

import cn.edu.whut.gumorming.model.vo.category.CategoryOptionVO;
import cn.edu.whut.gumorming.model.vo.tag.TagOptionVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 文章条件VO
 */
@Data
@Schema(description = "文章条件VO")
public class ArticleConditionVO {
    
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
     * 发表时间
     */
    @Schema(title = "发表时间")
    private Date createTime;
    
}