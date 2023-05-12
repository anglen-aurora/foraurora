package cn.foraurora.result;

import java.io.Serializable;

/**
 * 公共响应结果
 * @author huangchao | 黄超
 */
public class CommonResult<T> extends BaseResult implements Serializable {
    private static final long serialVersionUID = -2835552828916096962L;

    /**
     * 载荷数据
     * @mock "成功数据载荷（<T>）"
     */
    private T data;

    CommonResult(ResultStatus resultStatus, String customizeMsg, T data) {
        super(resultStatus, customizeMsg);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 返回一个简单的成功响应
     *
     * @return 返回一个无载荷的成功响应
     */
    public static CommonResult withSimpleSuccess() {
        return new CommonResult(ResultStatus.SUCCESS, null, null);
    }

    /**
     * 返回一个自定义的成功响应
     *
     * @param resultStatus 响应枚举
     * @return 返回一个无载荷自定义响应内容的成功响应结果
     */
    public static CommonResult withSimpleSuccess(ResultStatus resultStatus) {
        return new CommonResult(resultStatus, null, null);
    }

    /**
     * 返回一个成功响应
     *
     * @param data 成功响应的载荷数据
     * @param <S>  载荷数据的类型
     * @return 返回一个成功响应
     */
    public static <S> CommonResult<S> withData(S data) {
        return new CommonResult<>(ResultStatus.SUCCESS, null, data);
    }

    /**
     * 返回一个自定义错误信息的异常响应
     *
     * @param msg 自定义返回信息
     * @return 返回一个错误响应
     */
    public static CommonResult withError(String msg) {
        return new CommonResult<>(ResultStatus.FAIL, msg, null);
    }

    /**
     * 返回一个自定义响应内容的异常响应
     *
     * @param resultStatus 响应枚举
     * @param msg          自定义返回信息
     * @return 返回一个错误响应
     */
    public static <S> CommonResult withError(ResultStatus resultStatus, String msg, S data) {
        return new CommonResult<>(resultStatus, msg, data);
    }
}
