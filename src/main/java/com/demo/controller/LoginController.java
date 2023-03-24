package com.demo.controller;

import com.demo.entity.Customer;
import com.demo.entity.User;
import com.demo.repository.CustomerRepository;
import com.demo.repository.UserRepository;
import com.demo.service.LoginService;
import com.demo.utils.response.LoginAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

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
                                               @RequestParam("password") String password) {
//        System.out.println(username + " " + password);
        return new ResponseEntity<>(loginService.checkLoginAccount(username, password), HttpStatus.OK);
    }

    static User user = null;

    @GetMapping("/loginGoogle")
    public RedirectView currentUser(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        String url = "localhost:3000/welcome";
        String email = oAuth2AuthenticationToken.getPrincipal().getAttributes().get("email").toString();
        String name = oAuth2AuthenticationToken.getPrincipal().getAttributes().get("name").toString();
        String password = oAuth2AuthenticationToken.getPrincipal().getAttributes().get("at_hash").toString();
        System.out.println(email);
        System.out.println(name);
        System.out.println(password);
        user = new User(email, name, password, true, new Date(), email, "0987654321");
        User checkUser = userRepository.findCustomerById(email);
        if (checkUser == null) {
            userRepository.save(user);
            customerRepository.save(new Customer(email, false, user));
//             return new ResponseEntity<>(loginService.checkLoginAccount(email, password), HttpStatus.OK);
        }
//        UrlUtils.isValidRedirectUrl("http://localhost:6969/user/findAll");
//         return new ResponseEntity<>(loginService.checkLoginAccount(checkUser.getId(), checkUser.getPassword()), HttpStatus.OK);
//        return new RedirectView(url + "username=" + email + "&password=" + password);
        return new RedirectView(url);
    }

    @GetMapping("/loadUser")
    public ResponseEntity<LoginAPI> load() {
        return new ResponseEntity<>(loginService.checkLoginAccount(user.getId(), user.getPassword()), HttpStatus.OK);
    }

}
