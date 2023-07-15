package cn.edu.whut.gumorming.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.domain.dto
 * @createTime : 2023/7/11 10:58
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryUserDto {
    // 用户名
    private String userName;
    // 手机号
    private String phonenumber;
    // 状态
    private String status;
}