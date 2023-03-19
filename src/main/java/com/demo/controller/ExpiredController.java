package com.demo.controller;

import com.demo.entity.Customer_Invoice;
import com.demo.entity.Resident_Invoice;
import com.demo.service.CustomerExpiredService;
import com.demo.service.ResidentExpiredService;
import com.demo.utils.response.ExpiredResponse;
import com.demo.utils.response.FeeResponse;
import com.demo.utils.response.InvoiceCustomerResponse;
import com.demo.utils.response.InvoiceResidentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expired")
public class ExpiredController {
    @Autowired
    ResidentExpiredService residentExpiredService;

    @Autowired
    CustomerExpiredService customerExpiredService;
    @GetMapping("/findAllInvoiceC/{id}")
    public ResponseEntity<List<InvoiceCustomerResponse>> findAllInvoiceC(@PathVariable("id") String id){
        return new ResponseEntity<>(customerExpiredService.findAllCustomerInvoiceByCustomerID(id), HttpStatus.OK);
    }

    @GetMapping("/checkExpiredC/{id}")
    public ResponseEntity<List<ExpiredResponse>> getAllExpiredC(@PathVariable("id") String id) {
        return new ResponseEntity<>(customerExpiredService.checkExpired(id,
                customerExpiredService.findAllCustomerInvoiceByCustomerID(id)), HttpStatus.OK);
    }

    @GetMapping("/getFeeCutomer/{id_invoice}")
    public ResponseEntity<FeeResponse> getCustomerFee(@PathVariable("id_invoice") String id_invoice){
        return new ResponseEntity<>(customerExpiredService.getCustomerFee(id_invoice), HttpStatus.CREATED);
    }
    @GetMapping("/payC/{id_invoice}")
    public ResponseEntity<String> payFeeC(@PathVariable("id_invoice") String id_invoice){
        return new ResponseEntity<>(customerExpiredService.payFeeC(id_invoice), HttpStatus.OK);
    }

    @GetMapping("/findAllInvoiceR/{id}")
    public ResponseEntity<List<InvoiceResidentResponse>> findAllInvoice(@PathVariable("id") String id){
        return new ResponseEntity<>(residentExpiredService.findAllResidentInvoiceByResidentID(id), HttpStatus.OK);
    }

    @GetMapping("/checkExpiredR/{id}")
    public ResponseEntity<List<ExpiredResponse>> getAllExpired(@PathVariable("id") String id) {
        return new ResponseEntity<>(residentExpiredService.checkExpired(id,
                residentExpiredService.findAllResidentInvoiceByResidentID(id)), HttpStatus.OK);
    }

    @GetMapping("/getFeeResident/{id_invoice}")
    public ResponseEntity<FeeResponse> getResidentFee(@PathVariable("id_invoice") String id_invoice){
        return new ResponseEntity<>(residentExpiredService.getResidentFee(id_invoice), HttpStatus.CREATED);
    }

    @GetMapping("/payR/{id_invoice}")
    public ResponseEntity<String> payFeeR(@PathVariable("id_invoice") String id_invoice){
        return new ResponseEntity<>(residentExpiredService.payFeeR(id_invoice), HttpStatus.OK);
    }

}