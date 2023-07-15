package cn.edu.whut.gumorming.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户注册信息
 */
@Data
@Schema(description = "用户注册信息")
public class RegisterDTO {
    
    /**
     * 用户名
     */
//    @NotBlank(message = "邮箱不能为空")
//    @Email(message = "邮箱格式不正确")
    @Schema(title = "用户名")
    private String username;
    
    /**
     * 密码
     */
//    @NotBlank(message = "密码不能为空")
//    @Size(min = 6, message = "密码不能少于6位")
    @Schema(title = "密码")
    private String password;
    
    /**
     * 验证码
     */
//    @NotBlank(message = "验证码不能为空")
    @Schema(title = "验证码")
    private String code;
    
}