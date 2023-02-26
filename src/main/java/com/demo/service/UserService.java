package com.demo.service;

import com.demo.utils.request.UserDTO;
import com.demo.utils.response.UserResponseDTO;


import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponseDTO save(UserDTO dto);

    Optional<UserResponseDTO> findById(String id);

    List<UserResponseDTO> findAll();

    UserResponseDTO update(UserDTO dto, String id);

    String delete (String id);

}
