package com.demo.utils.response;

import lombok.Data;

@Data
public class ManagerResponseDTO {
    private String IdUser;

    private UserResponseDTO user;

    private boolean Role;
}
