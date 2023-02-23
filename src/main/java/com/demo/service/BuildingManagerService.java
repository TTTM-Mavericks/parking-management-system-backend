package com.demo.service;

import com.demo.entity.User;
import com.demo.utils.response.BuildingManagerResponse;

import java.util.List;

public interface BuildingManagerService {
    List<User> findSecurityByIdUser();

    BuildingManagerResponse BanOrUnbanSecurity(String id, boolean status);
}
