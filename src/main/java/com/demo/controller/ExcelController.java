package com.demo.controller;

import com.demo.service.ExcelService;
import com.demo.utils.excel.ExcelConfig;
import com.demo.utils.excel.dto.InvoiceCustomerExcel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/excel")
public class ExcelController {
    private final ExcelService excelService;
    @CrossOrigin(origins = "http://localhost:6969")
    @GetMapping("/export")
    public ResponseEntity<Resource> exportCustomerInvoice() throws Exception
    {
        List<InvoiceCustomerExcel> customerInvoiceList = excelService.getDataFromMultipleTables();

        if(!CollectionUtils.isEmpty(customerInvoiceList))
        {
            String fileName = "Customer Invoice Export" + ".xlsx";

            ByteArrayInputStream inputStream = ExcelConfig.exportCustomer(customerInvoiceList, fileName);

            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename='" + URLEncoder.encode(fileName, StandardCharsets.UTF_8))
                    .contentType(MediaType.parseMediaType("application/vnd.ms-excel; charset=UTF-8"))
                    .body(inputStreamResource);
        }
        else {
            throw new Exception("No data");
        }
    }
}
