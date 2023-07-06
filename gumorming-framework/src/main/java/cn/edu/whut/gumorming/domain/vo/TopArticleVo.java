package cn.edu.whut.gumorming.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.domain.vo
 * @createTime : 2023/7/6 20:40
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopArticleVo {
    private Long id;
    // 标题
    private String title;
    //访问量
    private Long viewCount;
}