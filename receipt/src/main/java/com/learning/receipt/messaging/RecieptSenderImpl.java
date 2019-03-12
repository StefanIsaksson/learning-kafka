package com.learning.receipt.messaging;

import com.learning.receipt.config.Topics;
import com.learning.receipt.model.Receipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RecieptSenderImpl implements RecieptSender {

    Logger logger = LoggerFactory.getLogger(RecieptSenderImpl.class);

    @Autowired
    private KafkaTemplate<String, String> template;

    @Override
    public void sendReceipt(Receipt receipt) {
        this.template.send(Topics.NEW_RECEIPTS_TOPIC, receipt.toString());
        logger.info(String.format("Message sent to topic [%s] with data: \n %s", Topics.NEW_RECEIPTS_TOPIC, receipt));
    }
}
