package com.learning.receipt.repository;

import com.learning.receipt.model.GeneratedReceipt;
import com.learning.receipt.model.Receipt;

import java.util.List;

public interface ReceiptRepository {

    List<Receipt> getReceipts();

    Receipt createReceipt(Receipt receipt);

    GeneratedReceipt updatePdfFileName(GeneratedReceipt generatedReceipt);

}
