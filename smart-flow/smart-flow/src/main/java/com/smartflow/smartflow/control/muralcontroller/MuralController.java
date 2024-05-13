package com.smartflow.smartflow.control.muralcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartflow.smartflow.dto.muraldto.MuralRequest;
import com.smartflow.smartflow.model.Mural;
import com.smartflow.smartflow.service.muralservice.MuralService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/mural")
public class MuralController {

    @Autowired
    private MuralService muralService;

    @PostMapping(path = "/save")
    public String saveInfo(@ModelAttribute MuralRequest muralRequest) {
        muralService.save(muralRequest);
        return "Aviso salvo com sucesso";
    }

    @GetMapping("/team/{teamId}")
    public List<Mural> getMuralsByTeamId(@PathVariable Integer teamId) {
        return muralService.getMuralsByTeamId(teamId);
    }

}
