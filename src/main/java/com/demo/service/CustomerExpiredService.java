package com.demo.service;

import com.demo.entity.Customer_Invoice;
import com.demo.entity.Resident_Invoice;
import com.demo.utils.response.ExpiredResponse;

import java.util.List;

public interface CustomerExpiredService {
    public List<Customer_Invoice> findAllCustomerInvoiceByCustomerID(String id);
    public List<ExpiredResponse> checkExpired(String id, List<Customer_Invoice> customerInvoices);
}
