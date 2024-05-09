package com.smartflow.smartflow.service.wikitextservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartflow.smartflow.model.Teams;
import com.smartflow.smartflow.model.WikiText;
import com.smartflow.smartflow.repository.TeamsRepository;
import com.smartflow.smartflow.repository.WikiTextRepository;

@Service
public class WikiTextServiceImpl implements WikiTextService {

    @Autowired
    private WikiTextRepository wikiTextRepository;

    @Autowired
    private TeamsRepository teamsRepository;

    @Override
    public void saveOrUpdateWikiText(Integer teamId, String text) {
        Teams team = teamsRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid team Id"));
        WikiText wikiText = wikiTextRepository.findByTeam(team);
        if (wikiText == null) {
            wikiText = new WikiText();
        }
        wikiText.setTeam(team);
        wikiText.setText(text);
        wikiTextRepository.save(wikiText);
    }

    @Override
    public WikiText getWikiTextByTeamId(Integer teamId) {
        Teams team = teamsRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid team Id"));
        return wikiTextRepository.findByTeam(team);
    }

}
