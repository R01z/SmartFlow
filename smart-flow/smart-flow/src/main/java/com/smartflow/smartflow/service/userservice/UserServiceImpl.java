package com.smartflow.smartflow.service.userservice;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartflow.smartflow.dto.userdto.UserDTO;
import com.smartflow.smartflow.model.User;
import com.smartflow.smartflow.repository.UserRepository;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String addUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getUserId(),
                userDTO.getName(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword()),
                new Timestamp(System.currentTimeMillis()));

        userRepository.save(user);

        return user.getName();
    }

}
