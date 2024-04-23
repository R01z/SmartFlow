package com.smartflow.smartflow.service.informationservice;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.smartflow.smartflow.dto.informationdto.InformationDTO;
import com.smartflow.smartflow.model.Information;
import com.smartflow.smartflow.model.Teams;
import com.smartflow.smartflow.repository.InformationRepository;
import com.smartflow.smartflow.repository.TeamsRepository;

@Service
public class InformationServiceImpl implements InformationService {

    @Autowired
    InformationRepository informationRepository;

    @Autowired
    TeamsRepository teamsRepository;

    @Override
    public Iterable<Information> findAll(Specification<Information> spec) {
        return informationRepository.findAll(spec);
    }

    @Override
    public String addInformation(InformationDTO informationDTO) {
        Information information = new Information();

        information.setName(informationDTO.getName());
        information.setDescription(informationDTO.getDescription());
        information.setLink(informationDTO.getLink());
        information.setFile(informationDTO.getFile());
        information.setUploadDate(new Timestamp(System.currentTimeMillis()));
        information.setTags(informationDTO.getTags());

        Teams team = teamsRepository.getReferenceById(informationDTO.getTeamId());
        information.setTeam(team);

        informationRepository.save(information);

        return "";
    }

}
