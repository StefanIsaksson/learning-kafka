import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class BookConsumerApp {


    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(BookConsumerApp.class);

        String bootstrapServer = "localhost:9092";
        String consumerGroup = "first_book_group";
        String topc = "books";

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");


        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topc));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                logger.info("Recieved new message. \n" +
                        " Timestamp: " + record.timestamp() + "\n" +
                        " Topic: " + record.topic() + "\n" +
                        " Partition: " + record.partition() +  "\n" +
                        " Offset: " + record.offset() +  "\n" +
                        " Key: " + record.key() +  "\n" +
                        " Value: " + record.value());
            }
        }
    }
}