package com.demo.controller;

import com.demo.service.UserService;
import com.demo.utils.request.UserDTO;
import com.demo.utils.response.UserResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserResponseDTO> save(@RequestBody String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        UserDTO dto = mapper.readValue(json, UserDTO.class);
        return new ResponseEntity<>(userService.save(dto), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<Optional<UserResponseDTO>> findById(@RequestParam("id") String id)
    {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserResponseDTO>> findAll()
    {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponseDTO> update(@RequestBody String json,@RequestParam("id") String id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        UserDTO dto = mapper.readValue(json, UserDTO.class);
        return new ResponseEntity<>(userService.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> update(@RequestParam("id") String id)
    {
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }
}