package com.learning.receipt.pdf;

import com.itextpdf.text.DocumentException;
import com.learning.receipt.model.Receipt;

import java.io.IOException;

public interface PDFGenerator {

    byte[] generatePDF(String fileName, Receipt receipt) throws IOException, DocumentException;
}
