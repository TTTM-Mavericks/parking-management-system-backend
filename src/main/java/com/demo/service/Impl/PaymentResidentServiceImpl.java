package com.demo.service.Impl;

import com.demo.entity.*;
import com.demo.repository.Invoice_R_Repository;
import com.demo.repository.Payment_R_Repository;
import com.demo.repository.ResidentRepository;
import com.demo.repository.Resident_Slot_Repository;
import com.demo.service.PaymentResidentService;
import com.demo.utils.request.PaymentResidentDTO;
import com.demo.utils.response.PaymentResidentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.demo.entity.Money.*;

@Service
public class PaymentResidentServiceImpl implements PaymentResidentService {
    @Autowired
    ResidentRepository residentRepository;

    @Autowired
    Payment_R_Repository payment_r_repository;

    @Autowired
    Invoice_R_Repository invoice_r_repository;

    @Autowired
    Resident_Slot_Repository resident_slot_repository;

    public PaymentResidentResponseDTO paymentResidentResponseDTO;

    @Override
    public PaymentResidentResponseDTO save(PaymentResidentDTO dto) {
        Resident resident = residentRepository.findById(dto.getIdUser()).get();

        List<Payment_R> list = payment_r_repository.findAll();
        Payment_R  payment_r = new Payment_R("PR" + list.size() + 1, dto.getTypeOfPayment(), resident);
        payment_r_repository.save(payment_r);

        double Total_Of_Money = 0;

        List<Resident_Slot> residentSlotList = resident_slot_repository.findResidentSlotByIdResident(dto.getIdUser());
        for(Resident_Slot resident_slot : residentSlotList)
        {
            String type_of_vehicle = resident_slot.getType_Of_Vehicle();
            switch(type_of_vehicle)
            {
                case "CAR":
                    Total_Of_Money += CAR_MONEY_BY_MONTH;
                    break;
                case "BIKE":
                    Total_Of_Money += BIKE_MONEY_BY_MONTH;
                    break;
                case "MOTO":
                    Total_Of_Money += MOTO_MONEY_BY_MONTH;
                    break;
            }
        }
        List<Resident_Invoice> list1 = invoice_r_repository.findAll();
        Resident_Invoice resident_invoice = new Resident_Invoice("RI" + list1.size() + 1, dto.getTypeOfPayment().equalsIgnoreCase("CASH") ? false : true, Total_Of_Money, dto.getDateOfPayment(), payment_r);
        invoice_r_repository.save(resident_invoice);

        paymentResidentResponseDTO = new PaymentResidentResponseDTO(dto.getIdUser(), "RI" + list1.size() + 1, "PR" + list.size() + 1,
                dto.getTypeOfPayment().equalsIgnoreCase("CASH") ? false : true,
                dto.getDateOfPayment(), Total_Of_Money, dto.getTypeOfPayment());
        return paymentResidentResponseDTO;
    }

    @Override
    public PaymentResidentResponseDTO findPayment() {
        return paymentResidentResponseDTO;
    }
}
