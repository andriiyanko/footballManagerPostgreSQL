package com.example.andy.footballmanagerpostgre.persistence.dao.service.interfaces;

import com.example.andy.footballmanagerpostgre.persistence.model.Team;

import java.util.List;
import java.util.Optional;

public interface ITeamService {
    Iterable<Team> findAllTeams();
    List<Team> findTeamByName (String name);
    Optional<Team> findTeamById (Integer id);
    Team saveTeam(Team team);
    List<Team> findTeamByCountry (String country);
    void deleteTeamById(Integer id);
    void deleteAllTeams();
}
