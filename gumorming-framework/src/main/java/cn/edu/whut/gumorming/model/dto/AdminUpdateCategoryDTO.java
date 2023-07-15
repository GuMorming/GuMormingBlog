package cn.edu.whut.gumorming.model.dto;

import lombok.Data;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog-Back
 * @Package : cn.edu.whut.gumorming.model.dto
 * @createTime : 2023/7/14 21:49
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
public class AdminUpdateCategoryDTO {
    private Long id;
    private Integer isDisable;
}