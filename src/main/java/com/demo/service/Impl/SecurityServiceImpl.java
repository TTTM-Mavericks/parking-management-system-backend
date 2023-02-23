package com.demo.service.Impl;

import com.demo.entity.*;
import com.demo.repository.*;
import com.demo.service.ResidentService;
import com.demo.service.SecurityService;
import com.demo.service.UserService;
import com.demo.utils.request.UpdateDTO;
import com.demo.utils.request.UserDTO;
import com.demo.utils.response.InvoiceCustomerResponse;
import com.demo.utils.response.InvoiceResidentResponse;
import com.demo.utils.response.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    Customer_Slot_Repository customer_slot_repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    Resident_Slot_Repository resident_slot_repository;

    @Autowired
    Invoice_C_Repository invoice_c_repository;

    @Autowired
    Payment_C_Repository payment_c_repository;

    @Autowired
    Invoice_R_Repository invoice_r_repository;

    @Autowired
    Payment_R_Repository payment_r_repository;

    @Autowired
    ResidentRepository residentRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public List<User> getAllCustomerFromBuilding(String Id_Building) {
        List<User> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        List<Customer_Slot> customer_slotList = customer_slot_repository.findAllSlotOfEachBuilding(Id_Building);
        for(Customer_Slot customerSlot : customer_slotList)
        {
            Booking booking = bookingRepository.findIdBookingByCustomerSlot(customerSlot.getId_C_Slot(), customerSlot.getArea().getId_Area());
//            System.out.println(booking);
            if(booking != null)
            {
                User user = userRepository.findById(booking.getCustomer().getIdUser()).get();
                if(!set.contains(booking.getCustomer().getIdUser()))
                {
                    list.add(user);
//                    System.out.println(user);
                    set.add(booking.getCustomer().getIdUser());
                }
            }
        }
        return list;
    }

    @Override
    public List<User> getAllResidentFromBuilding(String Id_Building) {
        List<User> list = new ArrayList<>();
        List<Resident_Slot> residentSlotList = resident_slot_repository.findAllSlotOfEachBuilding(Id_Building);
        for(Resident_Slot resident_slot : residentSlotList)
        {
            User user = userRepository.findById(resident_slot.getResident().getIdUser()).get();
            list.add(user);
            System.out.println(user);
        }
        return list;
    }

    @Override
    public InvoiceCustomerResponse searchCustomerInvoiceId(String Id_C_Invoice) {
        Customer_Invoice customer_invoice = invoice_c_repository.findById(Id_C_Invoice).get();
        return new InvoiceCustomerResponse(customer_invoice.getId_C_Invoice(), customer_invoice.getPayment_c().getId_Payment(),
                payment_c_repository.findById(customer_invoice.getPayment_c().getId_Payment()).get().getBooking().getId_Booking(),
                customer_invoice.getTotal_Of_Money(), customer_invoice.isStatus(),
                payment_c_repository.findById(customer_invoice.getPayment_c().getId_Payment()).get().getType());
    }

    @Override
    public List<InvoiceCustomerResponse> findAllCustomerInvoice() {
        List<InvoiceCustomerResponse> list = new ArrayList<>();
        List<Customer_Invoice>customer_invoices = invoice_c_repository.findAll();
        for (Customer_Invoice customer_invoice: customer_invoices)
        {
            list.add(new InvoiceCustomerResponse(customer_invoice.getId_C_Invoice(), customer_invoice.getPayment_c().getId_Payment(),
                    payment_c_repository.findById(customer_invoice.getPayment_c().getId_Payment()).get().getBooking().getId_Booking(),
                    customer_invoice.getTotal_Of_Money(), customer_invoice.isStatus(),
                    payment_c_repository.findById(customer_invoice.getPayment_c().getId_Payment()).get().getType()));
        }
        return list;
    }

    @Override
    public InvoiceResidentResponse searchResidentInvoiceId(String Id_R_Invoice) {
        Resident_Invoice resident_invoice = invoice_r_repository.findById(Id_R_Invoice).get();
        return new InvoiceResidentResponse(resident_invoice.getId_R_Invoice(), resident_invoice.getPayment_r().getId_Payment(),
                payment_r_repository.findById(resident_invoice.getPayment_r().getId_Payment()).get().getType(),
                resident_invoice.isStatus(), resident_invoice.getTotal_Of_Money(), resident_invoice.getTime(),
                payment_r_repository.findById(resident_invoice.getPayment_r().getId_Payment()).get().getResident().getIdUser());
    }

    @Override
    public List<InvoiceResidentResponse> findAllResidentInvoice() {
        List<InvoiceResidentResponse> list = new ArrayList<>();
        List<Resident_Invoice>residentInvoices = invoice_r_repository.findAll();
        for (Resident_Invoice resident_invoice: residentInvoices)
        {
            list.add(new InvoiceResidentResponse(resident_invoice.getId_R_Invoice(), resident_invoice.getPayment_r().getId_Payment(),
                    payment_r_repository.findById(resident_invoice.getPayment_r().getId_Payment()).get().getType(),
                    resident_invoice.isStatus(), resident_invoice.getTotal_Of_Money(), resident_invoice.getTime(),
                    payment_r_repository.findById(resident_invoice.getPayment_r().getId_Payment()).get().getResident().getIdUser()));
        }
        return list;
    }

    @Override
    public User createNewResident(User dto) {
        userRepository.save(dto);
        residentRepository.save(new Resident(dto.getId(),  userRepository.findById(dto.getId()).get(), true));
        return dto;
    }

    @Override
    public User createNewCustomer(User dto) {
        userRepository.save(dto);
        customerRepository.save(new Customer(dto.getId(), true, userRepository.findById(dto.getId()).get()));
        return dto;
    }

    @Override
    public User updateCustomer_Resident(String idUser, UpdateDTO user) {
        User dto = userRepository.findById(idUser).get();
        dto.setId(idUser);
        dto.setEmail(user.getEmail());
        dto.setGender(user.isGender());
        dto.setDateofbirth(user.getDateofbirth());
        dto.setPassword(user.getPassword());
        dto.setFullname(user.getFullname());
        dto.setPhone(user.getPhone());
        userRepository.save(dto);
        return dto;
    }
}
