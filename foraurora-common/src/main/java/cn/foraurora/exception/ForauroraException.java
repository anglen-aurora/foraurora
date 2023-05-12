package cn.foraurora.exception;

/**
 * 项目异常根类
 *
 * @author huangchao | 黄超
 */
public class ForauroraException extends RuntimeException {
    public ForauroraException(String message) {
        super(message);
    }

    public ForauroraException(String message, Throwable cause) {
        super(message, cause);
    }
}
