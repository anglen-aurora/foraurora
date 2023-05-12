package cn.foraurora.node;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

/**
 * @author huangchao
 */
@Component("c")
public class CNode extends NodeComponent {
    @Override
    public void process() throws Exception {
        System.out.println("c");
    }
}
