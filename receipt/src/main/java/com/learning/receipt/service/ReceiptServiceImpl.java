package com.learning.receipt.service;

import com.learning.receipt.messaging.RecieptSender;
import com.learning.receipt.model.GeneratedReceipt;
import com.learning.receipt.model.Receipt;
import com.learning.receipt.repository.ReceiptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    Logger logger = LoggerFactory.getLogger(ReceiptServiceImpl.class);


    @Autowired
    ReceiptRepository receiptRepository;

    @Autowired
    RecieptSender recieptSender;

    @Override
    public void createReceipt(Receipt receipt) throws IllegalArgumentException {
        boolean invoiceNumberExists = receiptRepository.getReceipts().stream().map(Receipt::getReceiptNumber).anyMatch(p -> p.equals(receipt.getReceiptNumber()));
        if (invoiceNumberExists) {
            throw new IllegalArgumentException(String.format("Receipt number %s already exists.", receipt.getReceiptNumber()));
        }

        receipt.setId(generateUUID());
        receiptRepository.createReceipt(receipt);
        recieptSender.sendReceipt(receipt);
    }

    @Override
    public List<Receipt> getReceipts() {
        return receiptRepository.getReceipts();
    }

    @Override
    public GeneratedReceipt updatePdfFileName(GeneratedReceipt generatedReceipt) {
        return receiptRepository.updatePdfFileName(generatedReceipt);
    }

    private String generateUUID() {
        return java.util.UUID.randomUUID().toString();
    }
}
