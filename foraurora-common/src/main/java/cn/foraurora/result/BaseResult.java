package cn.foraurora.result;

import java.util.Optional;

/**
 * 基本响应结果
 *
 * @author huangchao | 黄超
 */
public abstract class BaseResult {
    /**
     * 响应枚举
     */
    private ResultStatus resultStatus;
    /**
     * 响应代码
     * @mock 716001
     */
    private String code;
    /**
     * 响应信息
     * @mock 成功
     */
    private String msg;

    public ResultStatus getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BaseResult(ResultStatus resultStatus, String code, String msg) {
        this.resultStatus = resultStatus;
        this.code = code;
        this.msg = msg;
    }

    BaseResult(ResultStatus resultStatus) {
        this(resultStatus, resultStatus.getCode(), resultStatus.getMsg());
    }

    BaseResult(ResultStatus resultStatus, String customizeMsg) {
        this(resultStatus, resultStatus.getCode(), Optional.ofNullable(customizeMsg).orElse(resultStatus.getMsg()));
    }

    /**
     * 返回响应结果是否代表成功
     *
     * @return 响应结果表示成功则返回true
     */
    public boolean isSuccess() {
        return resultStatus.isPositive();
    }

}
