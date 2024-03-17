package com.smartflow.smartflow.service.teamsservice;

import java.util.List;

import com.smartflow.smartflow.dto.teamdto.TeamDTO;
import com.smartflow.smartflow.model.Teams;

public interface TeamsService {

    String addTeam(TeamDTO teamDTO);

    boolean deleteTeamById(Integer teamId);

    List<Teams> getAllTeams();

    Teams getTeamById(Integer teamId);

    String addMembers(Integer teamId, List<Integer> usersIds);
}
