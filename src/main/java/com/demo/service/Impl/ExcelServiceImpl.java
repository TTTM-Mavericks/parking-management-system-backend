package com.demo.service.Impl;

import com.demo.entity.Booking;
import com.demo.entity.Customer;
import com.demo.entity.Customer_Invoice;
import com.demo.entity.Payment_C;
import com.demo.repository.*;
import com.demo.service.ExcelService;
import com.demo.utils.excel.dto.InvoiceCustomerExcel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {
    private final UserRepository userRepository;

    private final CustomerRepository customerRepository;

    private final BookingRepository bookingRepository;

    private final Payment_C_Repository payment_c_repository;

    private final Invoice_C_Repository invoice_c_repository;

    @Override
    public List<InvoiceCustomerExcel> getDataFromMultipleTables() {
        List<InvoiceCustomerExcel>list= new ArrayList<>();
        List<Customer_Invoice>invoiceList = invoice_c_repository.findAll();
        if(CollectionUtils.isNotEmpty(invoiceList))
        {
            for(Customer_Invoice invoice : invoiceList)
            {
                Payment_C payment_c = payment_c_repository.findPaymentByInvoiceId(invoice.getId_C_Invoice());
                if(!ObjectUtils.isEmpty(payment_c))
                {
                    Booking booking = bookingRepository.findBookingByIdPayment(payment_c.getId_Payment());
                    if(!ObjectUtils.isEmpty(booking))
                    {
                        Customer customer = booking.getCustomer();
                        if(!ObjectUtils.isEmpty(customer))
                        {
                            list.add(new InvoiceCustomerExcel(
                                    invoice.getId_C_Invoice(), payment_c.getId_Payment(), customer.getIdUser(),
                                    invoice.getTotal_Of_Money(), (invoice.isStatus() == true ? "Completed" : "Not Completed"),
                                    payment_c.getType(), booking.getStartDate(), booking.getStartTime(),
                                    booking.getEndDate(), booking.getEndTime()));
                        }
                    }
                }
            }
        }
        return list;
    }

}
