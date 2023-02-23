package com.demo.service.Impl;

import com.demo.entity.Manager;
import com.demo.entity.User;
import com.demo.repository.ManagerRepository;
import com.demo.repository.UserRepository;
import com.demo.service.BuildingManagerService;
import com.demo.utils.response.BuildingManagerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingManagerServiceImpl implements BuildingManagerService{
    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findSecurityByIdUser() {
        List<User>list_security = userRepository.findSecurityByIdUser(3);
        return list_security;
    }

    @Override
    public BuildingManagerResponse BanOrUnbanSecurity(String id, boolean status) {
        User user = userRepository.findById(id).get();
        Manager manager = managerRepository.findById(id).get();
        manager.setStatus_Account(status);
        managerRepository.save(manager);

        BuildingManagerResponse buildingManager = new BuildingManagerResponse(user.getId(),
                user.getFullname(), user.getPassword(), user.isGender(), user.getDateofbirth(),
                user.getEmail(), user.getPhone(), manager.isStatus_Account());

        return buildingManager;
    }  
}
