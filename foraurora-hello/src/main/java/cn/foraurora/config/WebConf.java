package cn.foraurora.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author huangchao
 */
@Configuration
public class WebConf implements WebMvcConfigurer {
    @Resource(name = "druidInterceptor")
    private HandlerInterceptor druidInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(druidInterceptor);
    }
}
