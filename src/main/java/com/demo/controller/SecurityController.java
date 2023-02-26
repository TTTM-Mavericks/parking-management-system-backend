package com.demo.controller;

import com.demo.entity.User;
import com.demo.repository.Customer_Slot_Repository;
import com.demo.service.Customer_Slot_Service;
import com.demo.service.SecurityService;
import com.demo.service.UserService;
import com.demo.utils.request.UpdateDTO;
import com.demo.utils.request.UserAPI;
import com.demo.utils.request.UserDTO;
import com.demo.utils.response.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    SecurityService securityService;
    @Autowired
    Customer_Slot_Service customer_slot_service;

    @Autowired
    Customer_Slot_Repository customer_slot_repository;

    @PostMapping("/createCustomer")
    public ResponseEntity<User> createCustomer(@RequestBody String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User dto = mapper.readValue(json, User.class);
        return new ResponseEntity<>(securityService.createNewCustomer(dto), HttpStatus.OK);
    }

    @GetMapping("/ListAllCustomerFromBuilding/{Id_Building}")
    public ResponseEntity<List<UserAPI>> getAllCustomerFromBuilding(@PathVariable("Id_Building") String Id_Building)
    {
        return new ResponseEntity<>(securityService.getAllCustomerFromBuilding(Id_Building), HttpStatus.OK);
    }

    @GetMapping("/ListAllResidentFromBuilding/{Id_Building}")
    public ResponseEntity<List<User>>getAllResidentFromBuilding(@PathVariable("Id_Building") String Id_Building)
    {
        return new ResponseEntity<>(securityService.getAllResidentFromBuilding(Id_Building) , HttpStatus.OK);
    }

    @GetMapping("/searchCustomerInvoiceId/{Id_C_Invoice}")
    public ResponseEntity<InvoiceCustomerResponse>searchCustomerInvoiceId(@PathVariable("Id_C_Invoice") String Id_C_Invoice)
    {
        return new ResponseEntity<>(securityService.searchCustomerInvoiceId(Id_C_Invoice) , HttpStatus.OK);
    }
    @GetMapping("/findAllCustomerInvoice")
    public ResponseEntity<List<InvoiceCustomerResponse>>findAllCustomerInvoice(){
        return new ResponseEntity<>(securityService.findAllCustomerInvoice() , HttpStatus.OK);
    }

    @GetMapping("/searchResidentInvoiceId/{Id_R_Invoice}")
    public ResponseEntity<InvoiceResidentResponse>searchResidentInvoiceId(@PathVariable("Id_R_Invoice") String Id_R_Invoice)
    {
        return new ResponseEntity<>(securityService.searchResidentInvoiceId(Id_R_Invoice) , HttpStatus.OK);
    }
    @GetMapping("/findAllResidentInvoice")
    public ResponseEntity<List<InvoiceResidentResponse>>findAllResidentInvoice(){
        return new ResponseEntity<>(securityService.findAllResidentInvoice(), HttpStatus.OK);
    }

    @GetMapping("/findAllSlot/{Id_Building}")
    public ResponseEntity<List<PresentSlotOfBuilding>> findAllSlot(@PathVariable("Id_Building") String Id_Building)
    {
        return new ResponseEntity<>(customer_slot_service.findAllSlot(Id_Building), HttpStatus.OK);
    }

    @PostMapping("/createResident")
    public ResponseEntity<User> createResident(@RequestBody String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User dto = mapper.readValue(json, User.class);
        return new ResponseEntity<>(securityService.createNewResident(dto), HttpStatus.OK);
    }

    @PutMapping("/updateCustomer_Resident")
    public ResponseEntity<User> updateCustomer_Resident(@RequestBody String json, @RequestParam("idUser") String idUser) throws  Exception {
        ObjectMapper mapper = new ObjectMapper();
        UpdateDTO dto = mapper.readValue(json, UpdateDTO.class);
        return new ResponseEntity<>(securityService.updateCustomer_Resident(idUser, dto), HttpStatus.OK);
    }


}
