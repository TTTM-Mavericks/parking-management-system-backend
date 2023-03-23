package com.demo.utils.excel.dto;

import com.demo.entity.Customer_Invoice;
import com.demo.entity.Payment_C;
import com.demo.repository.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceCustomerExcel {
    private String Id_C_Invoice;
    private String Id_Payment;
    private String Id_Customer;
    private double Total_Of_Money;
    private String status_Invoice;
    private String Type_Of_Payment;
    private Date startDate;
    private String startTime;
    private Date endDate;
    private String endTime;
}
