package com.demo.service;

import com.demo.utils.request.BookingCustomerDTO;
import com.demo.utils.response.BookingCustomerResponseDTO;


public interface BookingCustomerService {
    BookingCustomerResponseDTO save(BookingCustomerDTO dto);

    BookingCustomerResponseDTO findBooking();
}
