package cn.edu.whut.gumorming.model.vo.blog;


import cn.edu.whut.gumorming.entity.SiteConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 博客信息
 *
 * @author ican
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "博客信息")
public class BlogInfoVO {
    
    /**
     * 文章数量
     */
    @Schema(title = "文章数量")
    private Long articleCount;
    
    /**
     * 分类数量
     */
    @Schema(title = "分类数量")
    private Long categoryCount;
    
    /**
     * 标签数量
     */
    @Schema(title = "标签数量")
    private Long tagCount;
    
    /**
     * 网站访问量
     */
    @Schema(title = "网站访问量")
    private String viewCount;
    
    /**
     * 网站配置
     */
    @Schema(title = "网站配置")
    private SiteConfig siteConfig;
    
}