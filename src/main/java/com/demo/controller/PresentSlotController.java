package com.demo.controller;

import com.demo.service.Customer_Slot_Service;
import com.demo.utils.response.Customer_Slot_Response_DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/present_slot")
public class PresentSlotController {
    @Autowired
    Customer_Slot_Service customer_slot_service;

    @GetMapping("/findAll/{Id_Building}")
    public ResponseEntity<List<Customer_Slot_Response_DTO>>present_slot(@PathVariable("Id_Building") String Id_Building)
    {
        return new ResponseEntity<>(customer_slot_service.findAllSlotOfEachBuilding(Id_Building) , HttpStatus.OK);
    }
}
