package com.learning.receipt.repository;

import java.util.List;

public interface ReceiptRepository {

    List<String> getFileNames();

    void createPDF(String pdfFileName, byte[] pdfFileBinaries);

    byte[] getPDF(String pdfFileName);

}
