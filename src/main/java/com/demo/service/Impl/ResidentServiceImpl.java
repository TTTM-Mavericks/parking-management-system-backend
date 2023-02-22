package com.demo.service.Impl;

import com.demo.entity.Resident;
import com.demo.repository.ResidentRepository;
import com.demo.repository.UserRepository;
import com.demo.service.ResidentService;
import com.demo.utils.request.ResidentDTO;
import com.demo.utils.response.ResidentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.demo.service.Impl.UserServiceImpl.mapperedToUserResponse;

@Controller
public class ResidentServiceImpl implements ResidentService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ResidentRepository residentRepository;

    @Override
    public ResidentResponseDTO save(ResidentDTO dto) {
        Resident resident = new Resident();
        resident.setStatus_Account(dto.isStatus_Account());
        resident.setIdUser(dto.getIdUser());
        resident.setUser(userRepository.findById(dto.getIdUser()).get());
        return mapperedToResident(residentRepository.save(resident));
    }

    @Override
    public Optional<ResidentResponseDTO> findById(String IdUser) {
        return Optional.of(mapperedToResident(residentRepository.findById(IdUser).get()));
    }

    @Override
    public List<ResidentResponseDTO> findAll() {
        return residentRepository.findAll().stream().map(r -> mapperedToResident(r)).collect(Collectors.toList());
    }

    @Override
    public ResidentResponseDTO update(ResidentDTO dto, String IdUser) {
        Resident resident = residentRepository.findById(IdUser).get();
        resident.setStatus_Account(dto.isStatus_Account());
        resident.setIdUser(dto.getIdUser());
        resident.setUser(userRepository.findById(dto.getIdUser()).get());
        return mapperedToResident(residentRepository.save(resident));
    }

    @Override
    public String delete(String IdUser) {
        residentRepository.deleteById(IdUser);
        return "deleted succesfully";
    }

    private ResidentResponseDTO mapperedToResident(Resident resident)
    {
        ResidentResponseDTO dto =  new ResidentResponseDTO();
        dto.setIdUser(resident.getIdUser());
        dto.setUser(mapperedToUserResponse(resident.getUser()));
        dto.setStatus_Account(resident.isStatus_Account());
        return dto;
    }
}
