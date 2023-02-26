package com.demo.controller;

import com.demo.service.PaymentCustomerService;
import com.demo.utils.request.PaymentCustomerDTO;
import com.demo.utils.response.PaymentCustomerReponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paymentCustomer")
public class PaymentCustomerController {
    @Autowired
    PaymentCustomerService paymentCustomerService;

    @PostMapping("/save")
    public ResponseEntity<PaymentCustomerReponseDTO> save(@RequestBody String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        PaymentCustomerDTO dto = mapper.readValue(json, PaymentCustomerDTO.class);
        return new ResponseEntity<>(paymentCustomerService.save(dto), HttpStatus.OK);
    }

    @GetMapping("/findPayment")
    public ResponseEntity<PaymentCustomerReponseDTO> findPayment()
    {
        return new ResponseEntity<>(paymentCustomerService.findPayment(), HttpStatus.OK);
    }
}
