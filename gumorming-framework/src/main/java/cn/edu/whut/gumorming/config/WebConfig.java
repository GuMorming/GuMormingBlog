package cn.edu.whut.gumorming.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许cookie
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }
    
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //需要追加byte，否则springdoc-openapi接口会响应Base64编码内容，导致接口文档显示失败
        // https://github.com/springdoc/springdoc-openapi/issues/2143
        // 解决方案
//        converters.add(fastJsonHttpMessageConverter());
        converters.add(new ByteArrayHttpMessageConverter());
        
    }
    
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(fastJsonHttpMessageConverter());
//        converters.add(new ByteArrayHttpMessageConverter());
    }
    
    
    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        //1.需要定义一个Convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 配置日期格式
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        // Long 转换为 String
        //3.设置id字段为字符串
        fastJsonConfig.setSerializeFilters((ValueFilter) (object, name, value) -> {
            if ("id".equalsIgnoreCase(name) || "parentId".equalsIgnoreCase(name)) {
                return value + "";
            }
            return value;
        });
//        SerializeConfig.globalInstance.put(Long.class, ToStringSerializer.instance);
//        fastJsonConfig.setSerializeConfig(SerializeConfig.globalInstance);
        
        // converter添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        
        return fastConverter;
    }
}