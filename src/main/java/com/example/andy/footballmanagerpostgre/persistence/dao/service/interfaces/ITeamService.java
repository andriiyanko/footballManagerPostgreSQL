package com.example.andy.footballmanagerpostgre.persistence.dao.service.interfaces;

import com.example.andy.footballmanagerpostgre.persistence.model.Team;

import java.util.Optional;

public interface ITeamService {
    Iterable<Team> findAllTeams();
    Team findTeamByName (String name);
    Optional<Team> findTeamById (Integer id);
    Team saveTeam(Team team);
}
