package cn.foraurora;

import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author huangchao
 */
@SpringBootTest
public class EsClientTest {

    private static final RequestOptions COMMON_OPTIONS;
    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
//        builder.addHeader("Authorization", "Bearer " + TOKEN);
        builder.setHttpAsyncResponseConsumerFactory(
                new HttpAsyncResponseConsumerFactory
                        .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }

    @Resource
    private RestHighLevelClient client;

    @Test
    public void createIndexIfNotExist() throws IOException {
        boolean exists = client.indices().exists(new GetIndexRequest("idx_md_file"), COMMON_OPTIONS);
        if (!exists){
            client.indices().create(new CreateIndexRequest("idx_md_file"),COMMON_OPTIONS);
        }
        Assertions.assertTrue(!exists);
    }

}
