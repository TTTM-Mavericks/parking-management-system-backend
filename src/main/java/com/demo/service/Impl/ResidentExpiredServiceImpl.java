package com.demo.service.Impl;

import com.demo.entity.Payment_R;
import com.demo.entity.Resident;
import com.demo.entity.Resident_Invoice;
import com.demo.repository.Invoice_R_Repository;
import com.demo.repository.Payment_R_Repository;
import com.demo.repository.ResidentRepository;
import com.demo.service.ResidentExpiredService;
import com.demo.utils.response.ExpiredResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResidentExpiredServiceImpl implements ResidentExpiredService {

    @Autowired
    ResidentRepository residentRepository;

    @Autowired
    Payment_R_Repository paymentRRepository;

    @Autowired
    Invoice_R_Repository invoice_r_repository;

    @Override
    public List<Resident_Invoice> findAllResidentInvoiceByResidentID(String id) {
        Optional<Resident> re = residentRepository.findById(id);
        List<Resident_Invoice> RIList = null;
        if (re != null) {
            List<Payment_R> pr = paymentRRepository.findAllPaymentByResident(id);
            for (Payment_R payment_r : pr) {
                Resident_Invoice ri = invoice_r_repository.findResident_InvoiceByResidentPayment(payment_r.getId_Payment());
                if (RIList == null) {
                    RIList = new ArrayList<>();
                }
                if(ri != null)
                    RIList.add(ri);
            }
        }
        return RIList;
    }

    @Override
    public List<ExpiredResponse> checkExpired(String id, List<Resident_Invoice> resident_invoiceList) {
        List<ExpiredResponse> expiredResidentResponseList = null;
        for (Resident_Invoice ri : resident_invoiceList) {
            Date end_date = ri.getTime();
            Date current_date = new Date();

            TimeZone vietnamTimeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
            Calendar calendar = Calendar.getInstance(vietnamTimeZone);
            calendar.setTime(current_date);
            int current_month = calendar.get(Calendar.MONTH) + 1; // Note: Calendar.MONTH is zero-based, so add 1
            int current_day = calendar.get(Calendar.DAY_OF_MONTH);

            calendar.setTime(end_date);
            int end_month = calendar.get(Calendar.MONTH) + 1 + 1;
            int end_day = calendar.get(Calendar.DAY_OF_MONTH);

            System.out.println(current_day + ":" + current_month);
            System.out.println(end_day + ":" + end_month);

            int expired = 0;
            float fine = 0;
            boolean warning = false;
            if (end_month == current_month) {
                expired = (current_day - end_day);
                fine = expired * 10;
                warning = true;
            } else if (end_month < current_month) {
                expired = Math.abs(current_day - end_day) + (current_month - end_month) * 31;
                fine = expired * 10;
                warning = true;
            }
            if (warning == true) {
                String current_time = current_date.getHours() + ":" + current_date.getMinutes();
                String end_time = "00:00";
                ExpiredResponse ex = new ExpiredResponse(ri.getPayment_r().getResident().getIdUser()
                        , current_date
                        , current_time
                        , end_date
                        , end_time
                        , expired
                        , fine
                        , warning);
                if (expiredResidentResponseList == null) {
                    expiredResidentResponseList = new ArrayList<>();
                }
                if(ex != null)
                expiredResidentResponseList.add(ex);
            }
        }
        return expiredResidentResponseList;
    }
}
