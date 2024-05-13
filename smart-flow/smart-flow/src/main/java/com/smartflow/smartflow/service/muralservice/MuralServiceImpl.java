package com.smartflow.smartflow.service.muralservice;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartflow.smartflow.dto.muraldto.MuralRequest;
import com.smartflow.smartflow.model.Mural;
import com.smartflow.smartflow.model.Teams;
import com.smartflow.smartflow.repository.MuralRepository;
import com.smartflow.smartflow.repository.TeamsRepository;

@Service
public class MuralServiceImpl implements MuralService {

    @Autowired
    private MuralRepository muralRepository;

    @Autowired
    private TeamsRepository teamsRepository;

    @Override
    public void save(MuralRequest muralRequest) {
        Teams team = teamsRepository.findById(muralRequest.getTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid team Id"));

        Timestamp now = new Timestamp(System.currentTimeMillis());

        Mural mural = new Mural();

        mural.setPublicationDate(now);
        mural.setTeam(team);
        mural.setTitle(muralRequest.getTitle());
        mural.setText(muralRequest.getText());

        muralRepository.save(mural);
    }

    @Override
    public List<Mural> getMuralsByTeamId(Integer teamId) {
        return muralRepository.findByTeamTeamId(teamId);
    }

}
