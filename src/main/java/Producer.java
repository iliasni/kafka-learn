import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String filename = "src/main/resources/near_earth_comets_asteroids.json";

    public static void main(String [] args) throws IOException {
        // Configure producer properties
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092"); // Kafka broker addresses
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Create kafka producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // Set topic to write to
        String topic = "stellar_objects";
        // Key will be the current date in 'yyyymmdd' format
        SimpleDateFormat myDateFormatter = new SimpleDateFormat("yyyyMMdd");

        // Parse JSON line by line
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        File file = new File(filename);
        JsonParser parser = mapper.getFactory().createParser(file);
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            if (parser.currentToken() == JsonToken.START_OBJECT) {
                // Assign POJO
                StellarObject stellarObject = new ObjectMapper().readValue(parser, StellarObject.class);
                // Create key and value to be written
                String key = myDateFormatter.format(stellarObject.getDiscoveryDate());
                String value = stellarObject.toString();
                // Create producer record
                ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
                // Send message
                producer.send(record, (metadata, e) -> {
                    if (e != null)
                        logger.info("Send failed for record " + record + " :" + e.getMessage());
                });
                logger.info("#".repeat(100));
                logger.info("Key: " + key);
                logger.info("Value: " + value);
                logger.info("#".repeat(100));
                logger.info("\n\n");
            }
        }
        // Close producer
        producer.close();
        // Close parser
        parser.close();

    }
}
