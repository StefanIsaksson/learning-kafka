package com.learning.receipt.messaging;

import com.learning.receipt.config.Topics;
import com.learning.receipt.model.GeneratedReceipt;
import com.learning.receipt.service.ReceiptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class GeneratedReceiptSenderImpl implements GeneratedReceiptSender {

    Logger logger = LoggerFactory.getLogger(GeneratedReceiptSenderImpl.class);

    @Autowired
    private KafkaTemplate<String, String> template;

    @Override
    public void sendGeneratedInvoice(GeneratedReceipt generatedReceipt) {
        this.template.send(Topics.GENERATEDS_INVOICE_QUEUE_NAME, generatedReceipt.toString());
        logger.info(String.format("Message sent to topic [%s] with data: \n %s", Topics.GENERATEDS_INVOICE_QUEUE_NAME, generatedReceipt));
    }
}
