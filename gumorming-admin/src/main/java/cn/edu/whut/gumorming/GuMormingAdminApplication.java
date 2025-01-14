package cn.edu.whut.gumorming;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming
 * @createTime : 2023/7/9 15:46
 * @Email : gumorming@163.com
 * @Description :
 */
@SpringBootApplication
@MapperScan("cn.edu.whut.gumorming.mapper")
@PropertySource("classpath:application-db.properties")
@PropertySource("classpath:application-mail.properties")
@PropertySource("classpath:application-qiniu.properties")
public class GuMormingAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuMormingAdminApplication.class, args);
    }
}