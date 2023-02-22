package com.demo.service;

import com.demo.utils.request.Resident_Slot_DTO;
import com.demo.utils.response.Customer_Slot_Response_DTO;
import com.demo.utils.response.Resident_Slot_Response_DTO;

import java.util.List;

public interface Resident_Slot_Service {
    Resident_Slot_Response_DTO save(Resident_Slot_DTO dto);

    List<Resident_Slot_Response_DTO> findAllSlotOfEachBuilding(String id_Building);

    Resident_Slot_Response_DTO find_Resident_Slot();
}
