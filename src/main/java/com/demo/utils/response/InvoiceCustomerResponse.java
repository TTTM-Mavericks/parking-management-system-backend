package com.demo.utils.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceCustomerResponse {
    private String Id_C_Invoice;

    private String Id_Payment;

    private Long id_Booking;

    private double Total_Of_Money;

    private boolean Status;

    private String TypeOfPayment; // Type of Payment
}
