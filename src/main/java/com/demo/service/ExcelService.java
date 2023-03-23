package com.demo.service;

import com.demo.utils.excel.dto.InvoiceCustomerExcel;

import java.util.List;

public interface ExcelService {
    List<InvoiceCustomerExcel> getDataFromMultipleTables();
}
