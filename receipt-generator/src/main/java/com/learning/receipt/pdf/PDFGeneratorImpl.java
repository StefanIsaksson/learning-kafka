package com.learning.receipt.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.learning.receipt.model.Receipt;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PDFGeneratorImpl implements PDFGenerator {

    @Override
    public byte[] generatePDF(String fileName, Receipt receipt) throws IOException, DocumentException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Document pdfDoc = new Document(PageSize.A4);
        PdfWriter.getInstance(pdfDoc, baos)
                .setPdfVersion(PdfWriter.PDF_VERSION_1_7);
        pdfDoc.open();

        Font myfont = new Font();
        myfont.setStyle(Font.NORMAL);
        myfont.setSize(11);
        pdfDoc.add(new Paragraph("\n"));

        Paragraph para = new Paragraph(receipt + "\n", myfont);
        para.setAlignment(Element.ALIGN_JUSTIFIED);
        pdfDoc.add(para);

        pdfDoc.close();
        return baos.toByteArray();
    }
}
