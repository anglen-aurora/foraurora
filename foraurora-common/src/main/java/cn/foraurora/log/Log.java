package cn.foraurora.log;

import org.springframework.core.annotation.AliasFor;

/**
 * 日志注解
 *
 * @author huangchao
 */
public @interface Log {

    /**
     * 日志信息
     *
     * @return
     */
    @AliasFor("value")
    String msg() default "";

    @AliasFor("msg")
    String value() default "";

    /**
     * 操作时间
     *
     * @return
     */
    String operateTime() default "";

    /**
     * 操作人
     *
     * @return
     */
    String operator() default "system";
}
