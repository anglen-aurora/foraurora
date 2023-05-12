package cn.foraurora.component;

import cn.foraurora.exception.ForauroraException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 容器感知类
 *
 * @author huangchao
 */
@Component
public class CustomizeApplicationContextAware implements ApplicationContextAware {
    private static ApplicationContext customizeApplicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        customizeApplicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        if (Objects.isNull(customizeApplicationContext)) {
            throw new ForauroraException("获取应用容器失败：容器未初始化");
        }

        return customizeApplicationContext;
    }
}
