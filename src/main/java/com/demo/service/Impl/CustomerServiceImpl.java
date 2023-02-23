package com.demo.service.Impl;

import com.demo.entity.Customer;
import com.demo.repository.CustomerRepository;
import com.demo.repository.UserRepository;
import com.demo.service.CustomerService;
import com.demo.utils.request.CustomerDTO;
import com.demo.utils.response.CustomerResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.demo.service.Impl.UserServiceImpl.mapperedToUserResponse;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public CustomerResponseDTO save(CustomerDTO dto) {
        Customer customer = new Customer(dto.getIdUser(), dto.isStatus_Account(), userRepository.findById(dto.getIdUser()).get());
        return mapperedToCustomer(customerRepository.save(customer));
    }

    @Override
    public Optional<CustomerResponseDTO> findById(String IdUser) {
        return Optional.of(mapperedToCustomer(customerRepository.findById(IdUser).get()));
    }

    @Override
    public List<CustomerResponseDTO> findAll() {
        return customerRepository.findAll().stream().map(c -> mapperedToCustomer(c)).collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDTO update(CustomerDTO dto, String IdUser) {
        Customer customer = customerRepository.findById(IdUser).get();
        customer.setStatus_Account(dto.isStatus_Account());
        customer.setIdUser(dto.getIdUser());
        customer.setCancel_of_payments(0);
        customer.setUser(userRepository.findById(dto.getIdUser()).get());
        return mapperedToCustomer(customerRepository.save(customer));
    }

    @Override
    public String delete(String IdUser) {
        customerRepository.deleteById(IdUser);
        return "delete successfully";
    }

    public static CustomerResponseDTO mapperedToCustomer(Customer customer)
    {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setIdUser(customer.getIdUser());
        dto.setUser(mapperedToUserResponse(customer.getUser()));
        dto.setStatus_Account(customer.isStatus_Account());
        return dto;
    }
}