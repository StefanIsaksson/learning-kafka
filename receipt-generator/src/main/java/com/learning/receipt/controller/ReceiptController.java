package com.learning.receipt.controller;

import com.learning.receipt.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class ReceiptController {

    @Autowired
    ReceiptService receiptService;

    @RequestMapping(value="/{fileName}", method=RequestMethod.GET)
    public ResponseEntity<byte[]> getPDF(@PathVariable(value = "fileName") String fileName) {
        //byte[] contents = receiptService.getPDFdocuments().get(fileName);
        byte[] contents = receiptService.getPDF(fileName);
        if(contents == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Receipt File not found.");        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));

        headers.setContentDispositionFormData(fileName, fileName);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/api/v1/receipt", method = RequestMethod.GET)
    public @ResponseBody List<String> getInvoices(){
        return receiptService.getInvoices();
    }

}
