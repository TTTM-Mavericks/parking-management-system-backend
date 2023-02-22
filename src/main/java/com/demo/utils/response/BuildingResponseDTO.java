package com.demo.utils.response;

import lombok.Data;


@Data
public class BuildingResponseDTO {
    private String Id_Building;

    private Integer Number_Of_Area;

    private Integer income;

    private ManagerResponseDTO manager;
}
