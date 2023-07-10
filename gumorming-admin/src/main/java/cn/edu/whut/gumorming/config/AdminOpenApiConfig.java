package cn.edu.whut.gumorming.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.config
 * @createTime : 2023/7/10 8:52
 * @Email : gumorming@163.com
 * @Description :
 */

@Configuration
public class AdminOpenApiConfig {
    
    
    private Info info() {
        return new Info()
                .title("GuMorming Blog Admin")
                .version("v1.0.0");
//                .license(license());
    }
    
    private ExternalDocumentation externalDocumentation() {
        return new ExternalDocumentation()
                .description("Github")
                .url("https://github.com/GuMorming/GuMormingBlog");
    }
    
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(info())
                .externalDocs(externalDocumentation());
    }
    
    
}