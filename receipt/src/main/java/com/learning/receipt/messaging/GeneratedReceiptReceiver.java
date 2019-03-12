package com.learning.receipt.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.receipt.config.Topics;
import com.learning.receipt.model.GeneratedReceipt;
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
public class GeneratedReceiptReceiver {

    Logger logger = LoggerFactory.getLogger(GeneratedReceiptReceiver.class);

    @Autowired
    ReceiptService receiptService;

    @KafkaListener(topics = Topics.GENERATEDS_INVOICE_QUEUE_NAME)
    public void listen(ConsumerRecord<String, String> record) {
        logger.info("Recieved new message. \n" +
                " Timestamp: " + record.timestamp() + "\n" +
                " Topic: " + record.topic() + "\n" +
                " Partition: " + record.partition() +  "\n" +
                " Offset: " + record.offset() +  "\n" +
                " Key: " + record.key() +  "\n" +
                " Value: " + record.value());
        GeneratedReceipt generatedReceipt = getGeneratedReceipt(record);
        receiptService.updatePdfFileName(generatedReceipt);
    }

    private GeneratedReceipt getGeneratedReceipt(ConsumerRecord<String, String> record) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(record.value(), GeneratedReceipt.class);
        } catch (IOException e) {
            logger.error("Failed to convert Kafka message from json to GeneratedReceipt object", e);
            throw new RuntimeException();
        }
    }

}