import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.Callback;
// import java.util.concurrent.Future;
import java.util.Properties;

public class ProducerApp {
    public static void main(String [] args) {
        // Configure producer properties
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092"); // Kafka broker addresses
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Create kafka producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        System.out.println("Instanced producer");

        // Create record to be sent
        String topic = "hsb";
        String key = "key1";
        String value = "Hello, Kafka!";
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        System.out.println("Instanced producer record");

        // Send the message asynchronously without checking if it was sent. future has metadata
        //Future<RecordMetadata> future = producer.send(record);

        // Send the message and provide callback to check when it is sent
        // Callback is executed in the producer’s IO thread
        producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata metadata, Exception e) {
                if (e != null)
                    System.out.println("Send failed for record " + record + " :" + e.getMessage());
            }
        });


        // Close the producer
        producer.close();

    }
}
