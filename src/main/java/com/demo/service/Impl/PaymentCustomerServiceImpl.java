package com.demo.service.Impl;

import com.demo.entity.Booking;
import com.demo.entity.Customer_Invoice;
import com.demo.entity.Payment_C;
import com.demo.repository.BookingRepository;
import com.demo.repository.Customer_Slot_Repository;
import com.demo.repository.Invoice_C_Repository;
import com.demo.repository.Payment_C_Repository;
import com.demo.service.PaymentCustomerService;
import com.demo.utils.request.PaymentCustomerDTO;
import com.demo.utils.response.PaymentCustomerReponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentCustomerServiceImpl implements PaymentCustomerService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    Invoice_C_Repository invoice_c_repository;

    @Autowired
    Payment_C_Repository payment_c_repository;

    @Autowired
    Customer_Slot_Repository customer_slot_repository;

    public PaymentCustomerReponseDTO paymentReponseDTO;

    @Override
    public PaymentCustomerReponseDTO save(PaymentCustomerDTO dto) {

        String Type_Of_Payment = dto.getType_Of_Payment();
        Long Id_Booking = dto.getId_Booking();

        Booking booking = bookingRepository.findById(Id_Booking).get();

        List<Customer_Invoice> invoiceList =  invoice_c_repository.findAll();
        List<Payment_C> paymentList =  payment_c_repository.findAll();

        Payment_C payment_c = new Payment_C("PC" + ((paymentList.size() == 0) ? 1 : paymentList.size() + 1 ), Type_Of_Payment, booking);
        payment_c_repository.save(payment_c);

        boolean Status_Invoice = Type_Of_Payment.equalsIgnoreCase("CASH") ? false : true;
        Customer_Invoice customer_invoice = new Customer_Invoice("IC" + ((invoiceList.size() == 0) ? 1 : invoiceList.size() + 1 ),
                20, Status_Invoice , payment_c);
        invoice_c_repository.save(customer_invoice);

        List<Customer_Invoice> invoiceList1 =  invoice_c_repository.findAll();
        List<Payment_C> paymentList1 =  payment_c_repository.findAll();

        paymentReponseDTO = new PaymentCustomerReponseDTO(Id_Booking, booking.getCustomer_slot().getId_C_Slot(), booking.getStartDate(),
                booking.getEndDate(), booking.getStartTime(), booking.getEndTime(), "PC" + paymentList1.size(),
                Type_Of_Payment, "IC" + invoiceList1.size(),  Status_Invoice);

        return paymentReponseDTO;
    }

    @Override
    public PaymentCustomerReponseDTO findPayment() {
        return paymentReponseDTO;
    }
}
