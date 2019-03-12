package com.learning.receipt.repository.util;

import com.learning.receipt.model.PDF;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PDFRowMapper implements RowMapper<PDF> {

    @Override
    public PDF mapRow(ResultSet rs, int rowNum) throws SQLException {
        PDF file = new PDF();
        file.setFileName(rs.getString("FILE_NAME"));
        file.setBinaryData(rs.getBytes("BINARY_DATA"));
        return file;
    }
}
