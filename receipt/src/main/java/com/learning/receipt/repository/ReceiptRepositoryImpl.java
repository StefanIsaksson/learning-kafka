package com.learning.receipt.repository;

import com.learning.receipt.model.GeneratedReceipt;
import com.learning.receipt.model.Receipt;
import com.learning.receipt.repository.util.ReceiptRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReceiptRepositoryImpl implements ReceiptRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Receipt> getReceipts() {
        List<Receipt> receipts = jdbcTemplate.query("select * from receipt", new ReceiptRowMapper());
        return receipts;
    }

    @Override
    public Receipt createReceipt(Receipt receipt) {
        jdbcTemplate.update("insert into receipt (ID, RECEIPT_NUMBER, CLIENT_NAME, CLIENT_ADDRESS, PAID_AMOUNT, PAYMENT_DATE, PDF_FILE_NAME) values (?,?,?,?,?,?,?)",
                receipt.getId(), receipt.getReceiptNumber(), receipt.getClientName(), receipt.getClientAddress(),
                receipt.getPaidAmount(), receipt.getPaymentDate(), receipt.getPdfFileName());
        return receipt;
    }

    @Override
    public GeneratedReceipt updatePdfFileName(GeneratedReceipt generatedReceipt) {
        jdbcTemplate.update("update receipt set PDF_FILE_NAME = ? where RECEIPT_NUMBER = ?",
                generatedReceipt.getFileName(), generatedReceipt.getReceiptNumber());
        return generatedReceipt;
    }
}
