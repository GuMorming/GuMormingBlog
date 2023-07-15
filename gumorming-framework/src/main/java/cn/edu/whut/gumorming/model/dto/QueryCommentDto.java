package cn.edu.whut.gumorming.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog-Back
 * @Package : cn.edu.whut.gumorming.model.dto
 * @createTime : 2023/7/12 11:15
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryCommentDto {
    // 评论类型
    Integer type;
    // 对应类型 ID
    Integer typeId;
}