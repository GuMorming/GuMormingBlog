package cn.edu.whut.gumorming;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : PACKAGE_NAME
 * @createTime : 2023/7/6 15:06
 * @Email : gumorming@163.com
 * @Description :
 */
@SpringBootApplication
@MapperScan("cn.edu.whut.gumorming.mapper")
@EnableScheduling
@PropertySource("classpath:application-db.properties")
@PropertySource("classpath:application-qiniu.properties")
@PropertySource("classpath:application-mail.properties")
public class GuMormingBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuMormingBlogApplication.class, args);
    }
}