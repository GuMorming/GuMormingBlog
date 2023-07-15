package cn.edu.whut.gumorming.model.vo.blog;

import cn.edu.whut.gumorming.model.vo.article.ArticleRankVO;
import cn.edu.whut.gumorming.model.vo.article.ArticleStatisticsVO;
import cn.edu.whut.gumorming.model.vo.category.CategoryVO;
import cn.edu.whut.gumorming.model.vo.tag.TagOptionVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 网站后台信息VO
 *
 * @author ican
 **/
@Data
@Builder
@Schema(description = "网站后台信息VO")
public class BlogBackInfoVO {
    
    /**
     * 访问量
     */
    @Schema(title = "访问量")
    private Integer viewCount;
    
    /**
     * 留言量
     */
    @Schema(title = "留言量")
    private Long messageCount;
    
    /**
     * 用户量
     */
    @Schema(title = "用户量")
    private Long userCount;
    
    /**
     * 文章量
     */
    @Schema(title = "文章量")
    private Long articleCount;
    
    /**
     * 分类统计
     */
    @Schema(title = "分类统计")
    private List<CategoryVO> categoryVOList;
    
    /**
     * 标签列表
     */
    @Schema(title = "标签列表")
    private List<TagOptionVO> tagVOList;
    
    /**
     * 文章贡献统计
     */
    @Schema(title = "文章贡献统计")
    private List<ArticleStatisticsVO> articleStatisticsList;
    
    /**
     * 文章浏览量排行
     */
    @Schema(title = "文章浏览量排行")
    private List<ArticleRankVO> articleRankVOList;
    
    /**
     * 一周访问量
     */
//    @Schema(title = "一周访问量")
//    private List<UserViewVO> userViewVOList;
}