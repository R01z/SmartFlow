package com.smartflow.smartflow.service.userservice;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.smartflow.smartflow.dto.logindto.LoginDTO;
import com.smartflow.smartflow.dto.userdto.UserDTO;
import com.smartflow.smartflow.model.Roles;
import com.smartflow.smartflow.model.User;
import com.smartflow.smartflow.repository.RolesRepository;
import com.smartflow.smartflow.repository.UserRepository;
import com.smartflow.smartflow.response.LoginResponse;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolesRepository rolesRepository;

    private User loggedUser;

    @Override
    public String addUser(UserDTO userDTO) {

        List<Roles> roles = new ArrayList<>();
        for (Roles role : userDTO.getRoles()) {
            roles.add(rolesRepository.findById(role.getId()).orElse(null));
        }

        User user = null;

        if (userDTO.getUserId() != null) {
            user = userRepository.findById(userDTO.getUserId()).orElse(null);
        }

        if (user == null) {
            user = new User(
                    userDTO.getUserId(),
                    userDTO.getName(),
                    userDTO.getEmail(),
                    this.passwordEncoder.encode(userDTO.getPassword()),
                    new Timestamp(System.currentTimeMillis()),
                    roles);
        } else {
            user.setEmail(userDTO.getEmail());
            user.setName(userDTO.getName());
            user.setPassword(userDTO.getPassword());
            user.setRoles(roles);
        }

        userRepository.save(user);

        return user.getName();
    }

    @Override
    public LoginResponse loginUsers(LoginDTO loginDTO) {
        User user1 = userRepository.findByEmail(loginDTO.getEmail());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> user = userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (user.isPresent()) {
                    setLoggedUser(user1);
                    return new LoginResponse("Login Success", true);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("password Not Match", false);
            }
        } else {
            return new LoginResponse("Email not exits", false);
        }
    }

    @Override
    public User getLoggedUser() {
        return loggedUser;
    }

    private void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
