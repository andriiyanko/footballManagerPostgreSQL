package com.example.andy.footballmanagerpostgre.persistence.dao.service.implementation;

import com.example.andy.footballmanagerpostgre.persistence.dao.repositories.TeamRepository;
import com.example.andy.footballmanagerpostgre.persistence.dao.service.interfaces.ITeamService;
import com.example.andy.footballmanagerpostgre.persistence.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements ITeamService {

    private TeamRepository teamRepository;

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Iterable<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> findTeamById(Integer id) {
        return teamRepository.findById(id);
    }

    @Override
    public List<Team> findTeamByName(String name) {
        return teamRepository.findTeamByName(name);
    }

    @Override
    public List<Team> findTeamByCountry(String country) {
        return teamRepository.findTeamByCountry(country);
    }

    @Override
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }


}
