package cn.edu.whut.gumorming.model.dto.mail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "邮箱DTO")
public class MailDTO {
    
    /**
     * 接收者邮箱号
     */
    @Schema(title = "接收者邮箱号")
    private String toEmail;
    
    /**
     * 主题
     */
    @Schema(title = "主题")
    private String subject;
    
    /**
     * 内容
     */
    @Schema(title = "内容")
    private String content;
    
    /**
     * 内容信息
     */
    @Schema(title = "内容信息")
    private Map<String, Object> contentMap;
    
    /**
     * 邮件模板
     */
    @Schema(title = "邮件模板")
    private String template;
}