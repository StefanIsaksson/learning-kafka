package com.learning.receipt.repository;

import com.learning.receipt.model.PDF;
import com.learning.receipt.repository.util.PDFRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReceiptRepositoryImpl implements ReceiptRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<String> getFileNames() {
        return jdbcTemplate.queryForList("select FILE_NAME from PDF_FILES", String.class);
    }

    @Override
    public void createPDF(String fileName, byte[] data) {
        jdbcTemplate.update("insert into PDF_FILES (FILE_NAME, BINARY_DATA) values (?,?)",
                fileName,
                data);
    }

    @Override
    public byte[] getPDF(String pdfFileName) {
        PDF pdf = jdbcTemplate.queryForObject("select * from PDF_FILES where FILE_NAME = ?", new PDFRowMapper(), pdfFileName);
        return pdf.getBinaryData();
    }

}
