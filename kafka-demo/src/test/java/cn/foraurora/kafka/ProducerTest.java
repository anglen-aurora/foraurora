package cn.foraurora.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author huangchao
 */
public class ProducerTest {
    KafkaProducer<String, String> producer;

    @BeforeEach
    public void startup() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "1.117.59.6:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // customize partitioner
//        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, ForauroraPartitioner.class.getName());

        // high payload
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 100);
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);

        // idempotence
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        properties.put(ProducerConfig.ACKS_CONFIG, "all");

        // transaction
//        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, IdUtil.fastSimpleUUID());

        producer = new KafkaProducer<>(properties);
    }

    @Test
    public void sendMsg() throws ExecutionException, InterruptedException, TimeoutException {
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            producer.send(new ProducerRecord<>("first", "hello_" + i), ((metadata, exception) -> {
                if (Objects.isNull(exception)) {
                    System.out.println("hello_" + finalI + " 投递成功");
                } else {
                    System.out.println("---------------hello_" + finalI + " 投递失败");
                    exception.printStackTrace();
                }
            }));
        }

    }

    @Test
    public void sendMsgWithPart() throws ExecutionException, InterruptedException, TimeoutException {
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            producer.send(new ProducerRecord<>("second", 2, "key1", "hello_" + i), ((metadata, exception) -> {
                if (Objects.isNull(exception)) {
                    System.out.println("hello_" + finalI + " 投递成功，分区：" + metadata.partition());
                } else {
                    System.out.println("---------------hello_" + finalI + " 投递失败");
                    exception.printStackTrace();
                }
            }));
        }

    }

    @Test
    public void sendMsgWithCustomizePartitioner() throws ExecutionException, InterruptedException, TimeoutException {
        for (int i = 8; i < 14; i++) {
            int finalI = i;
            producer.send(new ProducerRecord<>("first", "key1", "hello_" + i), ((metadata, exception) -> {
                if (Objects.isNull(exception)) {
                    System.out.println("hello_" + finalI + " 投递成功，分区：" + metadata.partition());
                } else {
                    System.out.println("---------------hello_" + finalI + " 投递失败");
                    exception.printStackTrace();
                }
            }));
        }
    }

    @Test
    public void sendMsgWithHighPayload() throws ExecutionException, InterruptedException, TimeoutException {
        for (int i = 1; i < 14; i++) {
            int finalI = i;
            producer.send(new ProducerRecord<>("second", "hello_" + i), ((metadata, exception) -> {
                if (Objects.isNull(exception)) {
                    System.out.println("hello_" + finalI + " 投递成功，分区：" + metadata.partition());
                } else {
                    System.out.println("---------------hello_" + finalI + " 投递失败");
                    exception.printStackTrace();
                }
            }));
        }
    }

    @Test
    public void sendMsgWithTransaction() {
        producer.initTransactions();
        producer.beginTransaction();

        try {
            for (int i = 0; i < 9; i++) {
                int idx = i;
                producer.send(new ProducerRecord<>("second", "foraurora_" + i), (metadata, exception) -> {
                    if (Objects.isNull(exception)) {
                        System.out.println("foraurora_" + idx + " 投递成功");
                    }
                });
            }
            int i = 10 / 0;
        } catch (Exception e) {
            producer.abortTransaction();
        }
    }

    @AfterEach
    public void dispose() {
        producer.close();
    }
}
