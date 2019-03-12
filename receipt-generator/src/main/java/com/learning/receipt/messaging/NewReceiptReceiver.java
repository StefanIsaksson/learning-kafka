package com.learning.receipt.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.receipt.config.Topics;
import com.learning.receipt.model.Receipt;
import com.learning.receipt.service.ReceiptService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NewReceiptReceiver {

    Logger logger = LoggerFactory.getLogger(NewReceiptReceiver.class);

    @Autowired
    ReceiptService receiptService;

    @KafkaListener(topics = Topics.NEW_RECEIPTS_TOPIC)
    public void listen(ConsumerRecord<String, String> record) {
        logger.info("Recieved new message. \n" +
                " Timestamp: " + record.timestamp() + "\n" +
                " Topic: " + record.topic() + "\n" +
                " Partition: " + record.partition() + "\n" +
                " Offset: " + record.offset() + "\n" +
                " Key: " + record.key() + "\n" +
                " Value: " + record.value());

        Receipt receipt = getReceipt(record);
        receiptService.createReceipt(receipt);
    }

    private Receipt getReceipt(ConsumerRecord<String, String> record) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(record.value(), Receipt.class);
        } catch (IOException e) {
            logger.error("Failed to convert Kafka message from json to Receipt object", e);
            throw new RuntimeException();
        }
    }

}