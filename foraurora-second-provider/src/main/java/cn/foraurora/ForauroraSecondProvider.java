package cn.foraurora;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huangchao | 黄超
 */
@EnableDubbo
@SpringBootApplication
public class ForauroraSecondProvider {
    public static void main(String[] args) {
        SpringApplication.run(ForauroraSecondProvider.class, args);
    }
}
