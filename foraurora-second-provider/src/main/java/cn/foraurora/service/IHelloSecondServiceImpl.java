package cn.foraurora.service;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * @author huangchao | 黄超
 */
@Slf4j
@Service
public class IHelloSecondServiceImpl {
    public String say2() {
        log.info("调用第二个dubbo服务...");
        return null;
    }
}
