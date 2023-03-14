package com.demo.service.Impl;

import com.demo.entity.*;
import com.demo.repository.*;
import com.demo.service.CustomerExpiredService;
import com.demo.utils.response.ExpiredResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerExpiredServiceImpl implements CustomerExpiredService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    Payment_C_Repository paymentCRepository;

    @Autowired
    Invoice_C_Repository invoice_c_repository;

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public List<Customer_Invoice> findAllCustomerInvoiceByCustomerID(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        List<Customer_Invoice> CIList = null;
        if (customer != null) {
            List<Booking> bk = bookingRepository.findBookingByCustomer(id);
            if ((bk != null)) {
                for (Booking b : bk) {
                    Payment_C pc = paymentCRepository.findPayment_C_By_Id_Booking(b.getId_Booking());
                    if(pc != null) {
                        Customer_Invoice ci = invoice_c_repository.findCustomer_Invoice_By_Id_Payment(pc.getId_Payment());
                        if (CIList == null) {
                            CIList = new ArrayList<>();
                        }
                        if (ci != null)
                            CIList.add(ci);
                    }
                }
            }
        }
        return CIList;
    }

    @Override
    public List<ExpiredResponse> checkExpired(String id, List<Customer_Invoice> customerInvoices) {
        List<ExpiredResponse> expiredResponseList = null;
        for (Customer_Invoice ci : customerInvoices) {
            Date end_date = ci.getPayment_c().getBooking().getEndDate();
            Date current_date = new Date();

            TimeZone vietnamTimeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
            Calendar calendar = Calendar.getInstance(vietnamTimeZone);
            calendar.setTime(current_date);
            int current_month = calendar.get(Calendar.MONTH) + 1; // Note: Calendar.MONTH is zero-based, so add 1
            int current_day = calendar.get(Calendar.DAY_OF_MONTH);
            int current_hours = calendar.get(Calendar.HOUR_OF_DAY) + 1;

            calendar.setTime(end_date);
            int end_month = calendar.get(Calendar.MONTH) + 1;
            int end_day = calendar.get(Calendar.DAY_OF_MONTH);
            String end_time = ci.getPayment_c().getBooking().getEndTime();
            int end_hours = Integer.parseInt(end_time.substring(0, 2));

            System.out.println(current_hours + ":" + current_day + ":" + current_month);
            System.out.println(end_hours + ":" + end_day + ":" + end_month);

//            int expired = 0;
//            float fine = 0;
//            boolean warning = false;
//            if (end_month == current_month) {
//                expired = (current_day - end_day);
//                fine = expired * 10;
//                warning = true;
//            } else if (end_month < current_month) {
//                expired = Math.abs(current_day - end_day) + (current_month - end_month) * 31;
//                fine = expired * 10;
//                warning = true;
//            }
//            if (warning == true) {
//                String current_time = current_date.getHours() + ":" + current_date.getMinutes();
//                String end_time = "00:00";
//                ExpiredResponse ex = new ExpiredResponse(ri.getPayment_r().getResident().getIdUser()
//                        , current_date
//                        , current_time
//                        , end_date
//                        , end_time
//                        , expired
//                        , fine
//                        , warning);
//                if (expiredResidentResponseList == null) {
//                    expiredResidentResponseList = new ArrayList<>();
//                }
//                if(ex != null)
//                    expiredResidentResponseList.add(ex);
//            }
        }
//        return expiredResidentResponseList;
        return null;
    }
}
