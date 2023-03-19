package com.demo.utils.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resident_Slot_DTO {
    private String typeOfPayment;
    private String idUser;
    private String id_Building;
    private Date dateOfPayment;
}
