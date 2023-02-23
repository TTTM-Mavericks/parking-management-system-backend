package com.demo.service;

import com.demo.entity.User;
import com.demo.utils.request.UpdateDTO;
import com.demo.utils.request.UserDTO;
import com.demo.utils.response.InvoiceCustomerResponse;
import com.demo.utils.response.InvoiceResidentResponse;
import com.demo.utils.response.UserResponseDTO;

import java.util.List;

public interface SecurityService {
    List<User> getAllCustomerFromBuilding(String Id_Building);

    List<User> getAllResidentFromBuilding(String Id_Building);

    InvoiceCustomerResponse searchCustomerInvoiceId(String Id_C_Invoice);

    List<InvoiceCustomerResponse> findAllCustomerInvoice();

    InvoiceResidentResponse searchResidentInvoiceId(String Id_R_Invoice);

    List<InvoiceResidentResponse> findAllResidentInvoice();

    User createNewResident(User dto);

    User createNewCustomer(User dto);

    User updateCustomer_Resident(String idUser, UpdateDTO dto);
}
