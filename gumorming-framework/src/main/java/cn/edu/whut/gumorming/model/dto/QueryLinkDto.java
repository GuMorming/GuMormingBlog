package cn.edu.whut.gumorming.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.domain.dto
 * @createTime : 2023/7/11 13:40
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryLinkDto {
    // 友链名
    private String name;
    // 状态
    private String status;
}