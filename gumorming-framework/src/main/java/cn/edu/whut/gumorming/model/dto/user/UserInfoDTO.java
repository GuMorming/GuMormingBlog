package cn.edu.whut.gumorming.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog-Back
 * @Package : cn.edu.whut.gumorming.model.dto
 * @createTime : 2023/7/12 18:42
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "博客用户信息")
public class UserInfoDTO {
    
    @Schema(title = "个人简介")
    private String intro;
    @NotBlank(message = "昵称不能为空")
    @Schema(title = "用户昵称")
    private String nickname;
    
    
    @Schema(title = "个人网站")
    private String webSite;
    
    
}