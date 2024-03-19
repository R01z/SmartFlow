package com.smartflow.smartflow.control.teamscontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartflow.smartflow.dto.teamdto.TeamDTO;
import com.smartflow.smartflow.model.Teams;
import com.smartflow.smartflow.service.teamsservice.TeamsService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/teams")
public class TeamsController {

    @Autowired
    private TeamsService teamsService;

    @PostMapping(path = "/save")
    public String saveUsers(@RequestBody TeamDTO teamDTO) {
        return teamsService.addTeam(teamDTO);
    }

    @PostMapping("/{teamId}/addMembers")
    public String addMembersToTeam(@PathVariable Integer teamId, @RequestBody List<Integer> userIds) {
        String message = teamsService.addMembers(teamId, userIds);
        return message;
    }

    @GetMapping(path = "/getAllTeams")
    public ResponseEntity<List<Teams>> getAllTeams() {
        List<Teams> allTeams = teamsService.getAllTeams();
        if (!allTeams.isEmpty()) {
            return ResponseEntity.ok(allTeams);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(path = "/getTeamById/{teamId}")
    public ResponseEntity<Teams> getTeamById(@PathVariable Integer teamId) {
        Teams team = teamsService.getTeamById(teamId);
        if (team != null) {
            return ResponseEntity.ok(team);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/deleteTeam/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable Integer teamId) {
        boolean deleted = teamsService.deleteTeamById(teamId);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
