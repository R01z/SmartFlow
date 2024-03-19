package com.smartflow.smartflow.service.teamsservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartflow.smartflow.dto.teamdto.TeamDTO;
import com.smartflow.smartflow.model.Teams;
import com.smartflow.smartflow.model.User;
import com.smartflow.smartflow.repository.TeamsRepository;
import com.smartflow.smartflow.repository.UserRepository;

@Service
public class TeamsServiceImpl implements TeamsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamsRepository teamsRepository;

    @Override
    public String addTeam(TeamDTO teamDTO) {
        List<User> members = new ArrayList<>();

        for (User user : teamDTO.getMembers()) {
            members.add(userRepository.findById(user.getUserId()).orElse(null));
        }

        Teams team = null;

        if (teamDTO.getTeamId() != null) {
            team = teamsRepository.findById(teamDTO.getTeamId()).orElse(null);
        }

        if (team == null) {
            team = new Teams(
                    teamDTO.getTeamId(),
                    teamDTO.getName(),
                    members,
                    teamDTO.getDescription());
        } else {
            team.setName(teamDTO.getName());
            team.setDescription(teamDTO.getDescription());
        }

        teamsRepository.save(team);

        return "Team Saved";
    }

    @Override
    public boolean deleteTeamById(Integer teamId) {
        teamsRepository.deleteById(teamId);
        return true;
    }

    @Override
    public List<Teams> getAllTeams() {
        return teamsRepository.findAll();
    }

    @Override
    public Teams getTeamById(Integer teamId) {
        return teamsRepository.findById(teamId).orElse(null);
    }

    @Override
    public String addMembers(Integer teamId, List<Integer> usersIds) {
        Teams team = teamsRepository.findById(teamId).orElse(null);

        List<User> users = userRepository.findAllById(usersIds);

        team.addMembers(users);

        teamsRepository.save(team);

        return "Members added";
    }

}
