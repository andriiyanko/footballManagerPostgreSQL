package com.example.andy.footballmanagerpostgre.persistence.dao.service.implementation;

import com.example.andy.footballmanagerpostgre.persistence.dao.repositories.TeamRepository;
import com.example.andy.footballmanagerpostgre.persistence.dao.service.interfaces.ITeamService;
import com.example.andy.footballmanagerpostgre.persistence.model.Team;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TeamServiceImpl implements ITeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Team findTeamByName(String name) {
        return teamRepository.findTeamByName(name);
    }

    @Override
    public Optional<Team> findTeamById(Integer id) {
        return teamRepository.findById(id);
    }
}
