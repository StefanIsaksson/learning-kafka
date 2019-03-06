import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class BookProducerApp {

    public static final String BOOTSTRAP_SERVER = "localhost:9092";
    public static final String TOPIC = "books";

    public static void main(String[] args) {

        final Logger logger = LoggerFactory.getLogger(BookProducerApp.class);

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        String message = "Hello World Book";

        producer.send(new ProducerRecord<String, String>(TOPIC, message), new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    logger.info("Recieved new metadata. \n" +
                            " Topic: " + recordMetadata.topic() + "\n" +
                            " Partition: " + recordMetadata.partition() +  "\n" +
                            " Offset: " + recordMetadata.offset() +  "\n" +
                            " Timestamp: " + recordMetadata.timestamp());
                } else {
                    logger.error("Error while producing", e);
                }
            }
        });

        producer.flush();

        producer.close();

    }
}
