package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.BuildingManagerService;
import com.demo.utils.response.BuildingManagerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buildingManager")
public class BuildingManagerController {
    @Autowired
    BuildingManagerService buildingManagerService;

    @GetMapping("/findSecurityByIdUser")
    public ResponseEntity<List<User>>findSecurityByIdUser(){
        return new ResponseEntity<>(buildingManagerService.findSecurityByIdUser(), HttpStatus.OK);
    }

    @PostMapping("/BanOrUnbanSecurity")
    public ResponseEntity<BuildingManagerResponse>BanOrUnbanSecurity(@RequestParam("id") String id,
                                                                     @RequestParam("id") boolean status){
        return new ResponseEntity<>(buildingManagerService.BanOrUnbanSecurity(id, status), HttpStatus.OK);
    }
}
