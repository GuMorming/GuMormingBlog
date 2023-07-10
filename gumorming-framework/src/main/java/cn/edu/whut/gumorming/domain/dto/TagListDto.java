package cn.edu.whut.gumorming.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.domain.dto
 * @createTime : 2023/7/10 10:39
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagListDto {
    // 标签名
    private String name;
    // 标签备注
    private String remark;
}