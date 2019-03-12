package com.learning.receipt.service;

import com.learning.receipt.model.Receipt;

import java.util.List;

public interface ReceiptService {

    void createReceipt(Receipt receipt);

    List<String> getInvoices();

    byte[] getPDF(String pdfFileName);
}
