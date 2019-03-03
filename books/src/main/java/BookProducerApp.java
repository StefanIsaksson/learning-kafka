import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class BookProducerApp {

    public static void main(String[] args){

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> bookProducer = new KafkaProducer<String, String>(props);

        String topic = "books";
        String message = "Hello World Book " + new Random().nextLong();
        try {
            bookProducer.send(new ProducerRecord<String, String>(topic, message));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bookProducer.close();
        }

    }
}
