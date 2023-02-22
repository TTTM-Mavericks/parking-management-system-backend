package com.demo.controller;

import com.demo.entity.Customer_Slot;
import com.demo.entity.User;
import com.demo.repository.Customer_Slot_Repository;
import com.demo.service.Customer_Slot_Service;
import com.demo.service.UserService;
import com.demo.utils.request.UserDTO;
import com.demo.utils.response.PresentSlotOfBuilding;
import com.demo.utils.response.UserResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    UserService userService;

    @Autowired
    Customer_Slot_Service customer_slot_service;

    @Autowired
    Customer_Slot_Repository customer_slot_repository;

    @PostMapping("/createCustomer")
    public ResponseEntity<UserResponseDTO> createCustomer(@RequestBody String json) throws JsonProcessingException, Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserDTO dto = mapper.readValue(json, UserDTO.class);
        return new ResponseEntity<>(userService.createCustomer(dto), HttpStatus.OK);
    }

    @GetMapping("/ListAllCustomer")
    public ResponseEntity<List<User>> ListAllCustomer()
    {
        return new ResponseEntity<>(userService.ListAllCustomer(), HttpStatus.OK);
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<User> updateCustomer(@RequestBody String json) throws JsonProcessingException, Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserDTO dto = mapper.readValue(json, UserDTO.class);
        return new ResponseEntity<>(userService.updateCustomer(dto), HttpStatus.OK);
    }

    @GetMapping("/findAllSlot/{Id_Building}")
    public ResponseEntity<List<PresentSlotOfBuilding>> findAllSlot(@PathVariable("Id_Building") String Id_Building)
    {
        return new ResponseEntity<>(customer_slot_service.findAllSlot(Id_Building), HttpStatus.OK);
    }
}
