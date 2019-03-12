package com.learning.receipt.service;

import com.itextpdf.text.DocumentException;
import com.learning.receipt.messaging.GeneratedReceiptSender;
import com.learning.receipt.model.GeneratedReceipt;
import com.learning.receipt.model.Receipt;
import com.learning.receipt.pdf.PDFGenerator;
import com.learning.receipt.repository.ReceiptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    Logger logger = LoggerFactory.getLogger(ReceiptServiceImpl.class);

    @Autowired
    GeneratedReceiptSender generatedReceiptSender;

    @Autowired
    PDFGenerator PDFGenerator;

    @Autowired
    ReceiptRepository receiptRepository;

    @Override
    public void createReceipt(Receipt receipt) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            String fileName = String.format("%s_%s.pdf", receipt.getReceiptNumber(), timestamp);
            byte[] pdf = PDFGenerator.generatePDF(fileName, receipt);
            receiptRepository.createPDF(fileName, pdf);
            logger.info(String.format("Generated PDF with file name: %s", fileName));

            generatedReceiptSender.sendGeneratedInvoice(new GeneratedReceipt(receipt.getReceiptNumber(), fileName));
        } catch (IOException e) {
            logger.error("Error creating Receipt", e);
        } catch (DocumentException e) {
            logger.error("Error creating Receipt", e);
        }
    }

    @Override
    public List<String> getInvoices() {
        return receiptRepository.getFileNames();
    }

    @Override
    public byte[] getPDF(String pdfFileName) {
        return receiptRepository.getPDF(pdfFileName);
    }

}
