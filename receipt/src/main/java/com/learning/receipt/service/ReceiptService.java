package com.learning.receipt.service;

import com.learning.receipt.model.GeneratedReceipt;
import com.learning.receipt.model.Receipt;

import java.util.List;

public interface ReceiptService {

    void createReceipt(Receipt receipt);

    List<Receipt> getReceipts();

    GeneratedReceipt updatePdfFileName(GeneratedReceipt generatedReceipt);
}
