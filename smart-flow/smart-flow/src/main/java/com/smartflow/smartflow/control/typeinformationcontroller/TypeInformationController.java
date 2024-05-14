package com.smartflow.smartflow.control.typeinformationcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartflow.smartflow.model.TypeInformation;
import com.smartflow.smartflow.service.typeinformationservice.TypeInformationService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/typeinformation")
public class TypeInformationController {

    @Autowired
    private TypeInformationService typeInformationService;

    @GetMapping(path = "/getAllTypes")
    public ResponseEntity<List<TypeInformation>> getAllTypes() {
        List<TypeInformation> allTypes = typeInformationService.getAllTypes();
        if (!allTypes.isEmpty()) {
            return ResponseEntity.ok(allTypes);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
