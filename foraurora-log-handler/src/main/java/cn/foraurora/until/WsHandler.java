package cn.foraurora.until;

import cn.foraurora.entity.WsMsg;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author huangchao
 */
@Component
public class WsHandler extends TextWebSocketHandler {
    private Logger logger = LoggerFactory.getLogger(WsHandler.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    private Map<String, WebSocketSession> connectSession = new HashMap<>(32);
    private Map<String, String> connectUser = new HashMap<>(32);

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        WsMsg wsMsg = objectMapper.readValue(new String(message.asBytes()), WsMsg.class);
        logger.info("接受信息...{}", wsMsg.getMsg());

        connectUser.put(wsMsg.getIdentifyId(), session.getId());

        List<String> receivers = wsMsg.getReceivers();
        for (String userIdentifyId : receivers) {
            // 跳过发送信息的用户\用户id为空串
            if (wsMsg.getIdentifyId().equals(userIdentifyId) || StrUtil.isBlank(userIdentifyId)) {
                continue;
            }

            String sessionId = connectUser.get(userIdentifyId);

            if (Objects.isNull(sessionId)) {
                logger.warn("用户未连接服务-[{}]", userIdentifyId);
            }

            WebSocketSession webSocketSession = connectSession.get(sessionId);
            try {
                webSocketSession.sendMessage(new TextMessage(wsMsg.getMsg()));
            } catch (Exception e) {
                logger.error("{}-发送消息失败-接收者[{}]-消息[{}]", sessionId, userIdentifyId, wsMsg.getMsg());
                logger.error("{}-错误堆栈", sessionId, e);
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        connectSession.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        connectSession.remove(session.getId());
    }
}
