package com.demo.service;

import com.demo.utils.request.Resident_Slot_DTO;
import com.demo.utils.response.Resident_Slot_Response_DTO;

public interface Resident_Slot_Service {
    Resident_Slot_Response_DTO save(Resident_Slot_DTO dto);

    Resident_Slot_Response_DTO find_Resident_Slot();
}
