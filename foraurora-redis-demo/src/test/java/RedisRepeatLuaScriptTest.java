import cn.foraurora.App;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author huangchao
 */
@SpringBootTest(classes = App.class)
public class RedisRepeatLuaScriptTest {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final String GET = "return redis.call('get',KEYS[1])";

    @Test
    public void test_repeat() {
        String key1Val = String.valueOf(
                redisTemplate.execute(new DefaultRedisScript<>(GET), Collections.singletonList("key1"), null));

        System.out.println(key1Val);
    }
}
