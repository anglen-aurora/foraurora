package cn.foraurora.node;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

/**
 * @author huangchao
 */
@Component("a")
public class ANode extends NodeComponent {
    @Override
    public void process() throws Exception {
        System.out.println("A");
    }
}
