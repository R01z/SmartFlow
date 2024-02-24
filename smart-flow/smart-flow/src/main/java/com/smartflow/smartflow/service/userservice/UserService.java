package com.smartflow.smartflow.service.userservice;

import com.smartflow.smartflow.dto.logindto.LoginDTO;
import com.smartflow.smartflow.dto.userdto.UserDTO;
import com.smartflow.smartflow.response.LoginResponse;

public interface UserService {

    String addUser(UserDTO userDTO);

    LoginResponse loginUsers(LoginDTO loginDTO);
}
