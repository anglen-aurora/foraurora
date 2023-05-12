package cn.foraurora;

import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author huangchao
 */
@SpringBootTest(classes = LiteApp.class)
public class AppTest {
    @Resource
    private FlowExecutor flowExecutor;

    @Test
    public void test1() {
        LiteflowResponse liteflowResponse = flowExecutor.execute2Resp("chain1", "arg");
    }
}
