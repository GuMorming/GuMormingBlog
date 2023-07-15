package cn.edu.whut.gumorming.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.domain.dto
 * @createTime : 2023/7/11 8:36
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryMenuDto {
    // 状态
    private String status;
    // 菜单名
    private String menuName;
}