package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.HeadManagerService;
import com.demo.utils.request.BuildingManagerDTO;
import com.demo.utils.request.RevenueDTO;
import com.demo.utils.request.SecurityDTO;
import com.demo.utils.request.UpdateDTO;
import com.demo.utils.response.BuildingManagerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/headManager")
public class HeadManageController {
    @Autowired
    HeadManagerService headManagerService;

    @GetMapping("/findAllBuildingManager")
    public ResponseEntity<List<SecurityDTO>> findAllSecurity(){
        return new ResponseEntity<>(headManagerService.findAllBuildingManager(), HttpStatus.OK);
    }

    @PutMapping("/BanOrUnbanBuildingManager")
    public ResponseEntity<SecurityDTO>BanOrUnbanBuildingManager(@RequestParam("idUser") String idUser,
                                                                @RequestParam("status") boolean status){
        return new ResponseEntity<>(headManagerService.BanOrUnbanBuildingManager(idUser, status), HttpStatus.OK);
    }

    @PutMapping("/updateBuildingManager")
    public ResponseEntity<SecurityDTO> updateBuildingManager(@RequestBody String json, @RequestParam("idUser") String idUser) throws  Exception {
        ObjectMapper mapper = new ObjectMapper();
        UpdateDTO dto = mapper.readValue(json, UpdateDTO.class);
        return new ResponseEntity<>(headManagerService.updateBuildingManager(idUser, dto), HttpStatus.OK);
    }

    @PostMapping("/createBuildingManager")
    public ResponseEntity<SecurityDTO> createBuildingManager(@RequestBody String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User dto = mapper.readValue(json, User.class);
        return new ResponseEntity<>(headManagerService.createBuildingManager(dto), HttpStatus.OK);
    }

    @GetMapping("/RevenueFromAllBuilding")
    public ResponseEntity<BuildingManagerDTO>RevenueFromEachBuilding(){
        return new ResponseEntity<>(headManagerService.RevenueFromAllBuilding(), HttpStatus.OK);
    }
}
