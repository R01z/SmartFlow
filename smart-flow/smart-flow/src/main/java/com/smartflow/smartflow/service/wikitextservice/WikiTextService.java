package com.smartflow.smartflow.service.wikitextservice;

import java.util.Optional;

import com.smartflow.smartflow.model.WikiText;

public interface WikiTextService {

    public void saveOrUpdateWikiText(Integer teamId, String text);

    public WikiText getWikiTextByTeamId(Integer teamId);
}
