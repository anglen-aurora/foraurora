package cn.foraurora.log;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author huangchao
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(cn.foraurora.log.Log)")
    public void logPoint(){}

    @Pointcut("execution(void cn.foraurora.*..*Controller.*(..))")
    public void logPointPlus(){}
}
