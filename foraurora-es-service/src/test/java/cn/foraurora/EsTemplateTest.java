package cn.foraurora;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import javax.annotation.Resource;

/**
 * @author huangchao
 */
@SpringBootTest
public class EsTemplateTest {
    @Resource
    ElasticsearchRestTemplate template;
}
