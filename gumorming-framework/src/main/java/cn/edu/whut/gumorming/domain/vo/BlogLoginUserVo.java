package cn.edu.whut.gumorming.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.domain.vo
 * @createTime : 2023/7/7 15:04
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogLoginUserVo {
    private String token;
    private UserInfoVo userinfo;
}