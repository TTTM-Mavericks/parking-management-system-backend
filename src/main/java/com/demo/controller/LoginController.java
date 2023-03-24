package com.demo.controller;

import com.demo.repository.CustomerRepository;
import com.demo.repository.UserRepository;
import com.demo.service.LoginService;
import com.demo.utils.response.LoginAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/loginAccount")
    public ResponseEntity<LoginAPI> checkLogin(@RequestParam("username") String username,
                                                @RequestParam("password") String password)
    {
//        System.out.println(username + " " + password);
        return new ResponseEntity<>(loginService.checkLoginAccount(username, password), HttpStatus.OK);
    }
}
