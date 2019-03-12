package com.learning.receipt.repository.util;

import com.learning.receipt.model.Receipt;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptRowMapper implements RowMapper<Receipt> {

    @Override
    public Receipt mapRow(ResultSet rs, int rowNum) throws SQLException {
        Receipt receipt = new Receipt();
        receipt.setId(rs.getString("ID"));
        receipt.setReceiptNumber(rs.getString("RECEIPT_NUMBER"));
        receipt.setClientName(rs.getString("CLIENT_NAME"));
        receipt.setClientAddress(rs.getString("CLIENT_ADDRESS"));
        receipt.setPaidAmount(rs.getBigDecimal("PAID_AMOUNT"));
        receipt.setPaymentDate(rs.getString("PAYMENT_DATE"));
        receipt.setPdfFileName(rs.getString("PDF_FILE_NAME"));
        return receipt;
    }
}
