package com.learning.receipt.controller;

import com.learning.receipt.model.Receipt;
import com.learning.receipt.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Controller
public class ReceiptController {

    @Autowired
    ReceiptService receiptService;

    @RequestMapping(value = "/api/v1/receipt", method = RequestMethod.POST)
    public @ResponseBody
    Receipt createInvoice(@RequestBody Receipt receipt) {
        try {
            receiptService.createReceipt(receipt);
            return receipt;
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(value = "/api/v1/receipt", method = RequestMethod.GET)
    public @ResponseBody
    List<Receipt> getInvoices() {
        return receiptService.getReceipts();
    }

}
