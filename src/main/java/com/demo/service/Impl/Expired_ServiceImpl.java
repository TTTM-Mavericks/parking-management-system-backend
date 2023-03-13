package com.demo.service.Impl;

import com.demo.entity.*;
import com.demo.repository.Invoice_C_Repository;
import com.demo.repository.Invoice_R_Repository;
import com.demo.service.Expired_Service;
import com.demo.utils.response.ExpiredResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class Expired_ServiceImpl implements Expired_Service {

    @Autowired
    Invoice_C_Repository invoice_c_repository;

    public ExpiredResponse checkExpiredC(String invoice_id) {
        Customer_Invoice ci = invoice_c_repository.findById_C_Invoice(invoice_id);
        Payment_C pc = ci.getPayment_c();
        Booking bk = pc.getBooking();
        Customer customer = bk.getCustomer();
        ExpiredResponse expiredResponse = null;

        Date current_date = new Date();
        String current_time = current_date.getHours() + ":" + current_date.getMinutes();
        Date end_date = bk.getEndDate();
        String end_time = bk.getEndTime();
        Long end_hours = Long.parseLong(end_time.substring(0, end_time.indexOf(':')));

        long diffInMillies = Math.abs(current_date.getTime() - end_date.getTime());
//        long day = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        long hours = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        long diffHours = hours - end_hours;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String now = simpleDateFormat.format(new Date());

        float fine = 0;
        if (diffHours >= 0) {
            fine = diffHours * 10;
            expiredResponse = new ExpiredResponse(customer.getIdUser(), now, current_time, end_date, end_time, diffHours, fine, true);
        }
        return expiredResponse;
    }

    @Autowired
    Invoice_R_Repository invoice_r_repository;

    public ExpiredResponse checkExpiredR(String invoice_id) {
        Resident_Invoice ri = invoice_r_repository.findResident_InvoiceByInvoiceID(invoice_id);
        Payment_R pr = ri.getPayment_r();
        ExpiredResponse expiredResponse = null;

        String id_user = pr.getResident().getIdUser();
        Date end_date = ri.getTime();
//        int dd = end_date.getDay();
        int MM = end_date.getMonth();
//        int yyyy = end_date.getYear();

        end_date.setMonth(MM + 1);

        Date current_date = new Date();
        String current_time = current_date.getHours() + ":" + current_date.getMinutes();

        long diffInMillies = Math.abs(current_date.getTime() - end_date.getTime());
        long diffDay = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
//        long hours = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
//        long diffHours = hours - end_hours;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String now = simpleDateFormat.format(new Date());

        float fine = 0;
        if (diffDay >= 1) {
            fine = diffDay * 5;
            expiredResponse = new ExpiredResponse(id_user, now, current_time, end_date, "00:00", diffDay, fine, true);
        }
        return expiredResponse;
    }

}
