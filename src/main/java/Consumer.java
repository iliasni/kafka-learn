import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    public static void main(String [] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092"); // Kafka broker addresses
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // Start from beginning of topic
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-learn"); // Set consumer group id

        // Topics to read from
        Collection<String> topicCollection = new ArrayList<>();
        topicCollection.add("stellar_objects");

        // Create kafka consumer
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(topicCollection);
            // Listen for messages every 100ms
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    String key = record.key();
                    String value = record.value();
                    logger.info(
                            String.format("Consumed event. Key = %s value = %s", key, value)
                    );
                }
            }
        }
    }
}
