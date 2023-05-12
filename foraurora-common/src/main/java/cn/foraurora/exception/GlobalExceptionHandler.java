package cn.foraurora.exception;

import cn.foraurora.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 基础全局异常处理器
 *
 * @author huangchao | 黄超
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResult httpRequestMethodNotSupportedExceptionHandler(
            HttpRequestMethodNotSupportedException e) {
        return CommonResult.withError("请选用支持的http调用方法 - " + e.getSupportedHttpMethods());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public CommonResult httpMessageNotReadableExceptionExceptionHandler(
            HttpMessageNotReadableException e) {
        return CommonResult.withError("请检查请求体参数是否正确");
    }

    /**
     * 处理项目异常
     *
     * @param forauroraException 项目异常对象
     * @return 统一返回对象
     */
    @ExceptionHandler(ForauroraException.class)
    public CommonResult handleForauroraException(ForauroraException forauroraException) {
        log.error("ForauroraException被抛出，错误堆栈：", forauroraException);

        return CommonResult.withError(forauroraException.getLocalizedMessage());
    }

    /**
     * 处理异常（兜底方法）
     *
     * @param e 未捕获的异常
     * @return 统一返回对象
     */
    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e) {
        log.error("未知异常，错误堆栈：", e);
        return CommonResult.withError(e.getLocalizedMessage());
    }

}
