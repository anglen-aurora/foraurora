package cn.foraurora.result;

/**
 * 后台响应结果枚举
 *
 * @author huangchao | 黄超
 */
public enum ResultStatus {

    /**
     * 默认成功响应结果
     */
    SUCCESS("716001", "成功") {
        @Override
        boolean isPositive() {
            return true;
        }
    },

    /**
     * 默认失败结果
     */
    FAIL("715999", "失败") {
        @Override
        boolean isPositive() {
            return false;
        }
    };

    private String code;
    private String msg;

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

    ResultStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 是否成功
     *
     * @return 响应结果表示成功返回true
     */
    abstract boolean isPositive();
}
