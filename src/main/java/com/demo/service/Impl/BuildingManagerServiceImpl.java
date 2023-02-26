package com.demo.service.Impl;

import com.demo.entity.Building;
import com.demo.entity.Manager;
import com.demo.entity.User;
import com.demo.repository.BuildingRepository;
import com.demo.repository.ManagerRepository;
import com.demo.repository.UserRepository;
import com.demo.service.BuildingManagerService;
import com.demo.utils.request.RevenueDTO;
import com.demo.utils.request.SecurityDTO;
import com.demo.utils.request.UpdateDTO;
import com.demo.utils.response.BuildingManagerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingManagerServiceImpl implements BuildingManagerService{
    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BuildingRepository buildingRepository;
    @Override
    public List<SecurityDTO> findAllSecurity() {
        List<User>list_security = userRepository.findSecurityByIdUser(3);
        List<SecurityDTO> list = new ArrayList<>();
        for(User user : list_security)
        {
            Manager manager = managerRepository.findById(user.getId()).get();
            list.add(new SecurityDTO(user.getId(), user.getFullname(), user.getPassword(), user.isGender(), user.getDateofbirth(),
                    user.getEmail(), user.getPhone(), manager.isStatus_Account(), manager.getRole()));
        }
        return list;
    }

    @Override
    public SecurityDTO BanOrUnbanSecurity(String idUser, boolean status) {
        System.out.println(idUser);
        System.out.println(status);
        User user = userRepository.findById(idUser).get();
        Manager manager = managerRepository.findById(idUser).get();
        manager.setIdUser(idUser);
        manager.setStatus_Account(status);
        managerRepository.save(manager);

        return new SecurityDTO(user.getId(), user.getFullname(), user.getPassword(), user.isGender(), user.getDateofbirth(),
                user.getEmail(), user.getPhone(), manager.isStatus_Account(), manager.getRole());
    }

    @Override
    public SecurityDTO updateSecurity(String idUser, UpdateDTO user) {
        User dto = userRepository.findSecurityByIdManager(idUser, 3);
        dto.setId(idUser);
        dto.setEmail(user.getEmail());
        dto.setGender(user.isGender());
        dto.setDateofbirth(user.getDateofbirth());
        dto.setPassword(user.getPassword());
        dto.setFullname(user.getFullname());
        dto.setPhone(user.getPhone());
        userRepository.save(dto);
        Manager manager = managerRepository.findById(idUser).get();
        return new SecurityDTO(idUser, user.getFullname(), user.getPassword(), user.isGender(), user.getDateofbirth(),
                user.getEmail(), user.getPhone(), manager.isStatus_Account(), manager.getRole());
    }

    @Override
    public SecurityDTO createSecurity(User user) {
        User dto = new User();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setGender(user.isGender());
        dto.setDateofbirth(user.getDateofbirth());
        dto.setPassword(user.getPassword());
        dto.setFullname(user.getFullname());
        dto.setPhone(user.getPhone());
        userRepository.save(dto);
        managerRepository.save(new Manager(user.getId(), userRepository.findById(user.getId()).get(), 3));
        Manager manager = managerRepository.findById(user.getId()).get();
        return new SecurityDTO(user.getId(), user.getFullname(), user.getPassword(), user.isGender(), user.getDateofbirth(),
                user.getEmail(), user.getPhone(), manager.isStatus_Account(), manager.getRole());
    }

    @Override
    public List<RevenueDTO> RevenueFromEachBuilding() {
        List<Building>list = buildingRepository.findAll();
        List<RevenueDTO> revenueDTOList = new ArrayList<>();
        for (Building building : list)
        {
            revenueDTOList.add(new RevenueDTO(building.getId_Building(), building.getIncome(),building.getManager().getIdUser()));
        }
        return revenueDTOList;
    }

    @Override
    public SecurityDTO findByIdSecurity(String id_Manager) {
        Manager manager = managerRepository.findById(id_Manager).get();
        User user = userRepository.findSecurityByIdManager(id_Manager, 3);
        return new SecurityDTO(user.getId(), user.getFullname(), user.getPassword(), user.isGender(), user.getDateofbirth(),
                user.getEmail(), user.getPhone(), manager.isStatus_Account(), manager.getRole());
    }
}
