package com.smartflow.smartflow.control.usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartflow.smartflow.dto.userdto.UserDTO;
import com.smartflow.smartflow.service.userservice.UserService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public String saveUsers(@RequestBody UserDTO userDTO) {
        String id = userService.addUsers(userDTO);
        return id;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUsers(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = userService.loginUsers(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }
}
