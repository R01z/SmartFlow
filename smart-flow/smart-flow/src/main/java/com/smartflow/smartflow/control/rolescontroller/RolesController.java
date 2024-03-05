package com.smartflow.smartflow.control.rolescontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartflow.smartflow.model.Roles;
import com.smartflow.smartflow.service.rolesservice.RolesService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/roles")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping(path = "/getAllRoles")
    public ResponseEntity<List<Roles>> getAllRoles() {
        List<Roles> allRoles = rolesService.getAllRoles();
        if (!allRoles.isEmpty()) {
            return ResponseEntity.ok(allRoles);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
