package com.demo.service;

import com.demo.entity.Resident_Invoice;
import com.demo.utils.response.ExpiredResponse;

import java.util.List;

public interface ResidentExpiredService {
    public List<Resident_Invoice> findAllResidentInvoiceByResidentID(String id);
    public List<ExpiredResponse> checkExpired(String id, List<Resident_Invoice> resident_invoiceList);
}
