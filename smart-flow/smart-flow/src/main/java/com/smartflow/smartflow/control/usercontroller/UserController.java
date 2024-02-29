package com.smartflow.smartflow.control.usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartflow.smartflow.dto.logindto.LoginDTO;
import com.smartflow.smartflow.dto.userdto.UserDTO;
import com.smartflow.smartflow.response.LoginResponse;
import com.smartflow.smartflow.model.User;
import com.smartflow.smartflow.service.userservice.UserService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public String saveUsers(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUsers(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = userService.loginUsers(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping(path = "/loggedUser")
    public ResponseEntity<?> getLoggedUser() {
        User loggedUser = userService.getLoggedUser();
        if (loggedUser != null) {
            return ResponseEntity.ok(loggedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
