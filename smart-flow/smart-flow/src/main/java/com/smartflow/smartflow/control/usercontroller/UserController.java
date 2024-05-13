package com.smartflow.smartflow.control.usercontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartflow.smartflow.dto.logindto.LoginDTO;
import com.smartflow.smartflow.dto.userdto.UserDTO;
import com.smartflow.smartflow.response.LoginResponse;
import com.smartflow.smartflow.model.Teams;
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

    @GetMapping(path = "/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        if (!allUsers.isEmpty()) {
            return ResponseEntity.ok(allUsers);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(path = "/getUserById/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        boolean deleted = userService.deleteUserById(userId);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Função para obter todos os times que um usuário faz parte
    @GetMapping("/{userId}/teams")
    public List<Teams> getUserTeams(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return user.getTeams();
        } else {
            // Lidar com o caso em que o usuário não foi encontrado
            return new ArrayList<>();
        }
    }

    @GetMapping("/{userId}/isAdmin")
    public ResponseEntity<Boolean> isUserAdmin(@PathVariable Integer userId) {
        boolean isAdmin = userService.isUserAdmin(userId);
        return ResponseEntity.ok(isAdmin);
    }

}
