package cn.foraurora.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author huangchao
 */
@SpringBootTest
public class KafkaConsumerTest {
    private KafkaConsumer<String, String> consumer;

    @BeforeEach
    public void init() {
        Properties properties = new Properties();

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "1.117.59.6:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");


        consumer = new KafkaConsumer<>(properties);
    }

    @Test
    public void singleTopicConsume() {
        consumer.subscribe(Collections.singletonList("first"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(3));
            records.forEach(System.out::println);
        }
    }

    @Test
    public void singlePartitionConsume() {
        consumer.assign(Collections.singletonList(new TopicPartition("first", 0)));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(3));
            records.forEach(System.out::println);
        }
    }

    @AfterEach
    public void dispose() {
        consumer.close();
    }
}
