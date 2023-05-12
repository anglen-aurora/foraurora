package cn.foraurora.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author huangchao
 */
@Data
public class WsMsg implements Serializable {
    private static final long serialVersionUID = -791674702508233383L;

    private String identifyId;

    private String msg;

    private List<String> receivers;
}
