package com.smartflow.smartflow.service.muralservice;

import java.util.List;

import com.smartflow.smartflow.dto.muraldto.MuralRequest;
import com.smartflow.smartflow.model.Mural;

public interface MuralService {

    public void save(MuralRequest muralRequest);

    public List<Mural> getMuralsByTeamId(Integer teamId);
}
