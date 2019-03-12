package com.learning.receipt.messaging;

import com.learning.receipt.model.Receipt;

public interface RecieptSender {

    void sendReceipt(Receipt receipt);
}
