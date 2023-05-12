package cn.foraurora;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @author huangchao | 黄超
 */
@Slf4j
@SpringBootApplication
public class LogProductApplication {
    public static void main(String[] args) {

        SpringApplication.run(LogProductApplication.class, args);

        for (int i = 0; i < 1000000; i++) {
//        for (int i = 0; i < 10; i++) {
            MDC.put("Constant.TRACE_ID", IdUtil.fastSimpleUUID());

            if (i % 2 == 0) {
                log.info(getOneRandomChar());
            }

            if (i % 3 == 0) {
                log.warn(getOneRandomChar());
            }

            if (i % 5 == 0) {
                try {
                    int j = i / 0;
                } catch (Exception e) {
                    log.error("错误堆栈", e);
                }
            }
        }
    }

    public static String getOneRandomChar() {

        StringBuilder str = new StringBuilder();
        int hightPos;
        int lowPos;

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            // 两个数字与GBK编码的知识相关，此处不做详解，直接百度GBK编码即可
            hightPos = (176 + random.nextInt(39));
            lowPos = (161 + random.nextInt(93));
            byte[] b = new byte[2];
            b[0] = (Integer.valueOf(hightPos)).byteValue();
            b[1] = (Integer.valueOf(lowPos)).byteValue();

            try {
                // 将字节转化为汉字
                str.append(new String(b, "GBK"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return str.toString();
    }
}
