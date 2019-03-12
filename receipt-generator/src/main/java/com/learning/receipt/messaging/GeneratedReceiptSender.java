package com.learning.receipt.messaging;

import com.learning.receipt.model.GeneratedReceipt;

public interface GeneratedReceiptSender {

    void sendGeneratedInvoice(GeneratedReceipt generatedReceipt);
}
