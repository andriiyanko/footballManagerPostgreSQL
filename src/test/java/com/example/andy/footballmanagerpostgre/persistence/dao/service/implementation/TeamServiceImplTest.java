package com.example.andy.footballmanagerpostgre.persistence.dao.service.implementation;

import com.example.andy.footballmanagerpostgre.persistence.dao.repositories.TeamRepository;
import com.example.andy.footballmanagerpostgre.persistence.dao.service.interfaces.ITeamService;
import com.example.andy.footballmanagerpostgre.persistence.model.Team;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TeamServiceImplTest {

    @TestConfiguration
    static class TeamServiceImplTestContextConfiguration{
        @Bean
        public ITeamService teamService(){
            return new TeamServiceImpl();
        }
    }

    @Autowired
    private ITeamService teamService;

    @MockBean
    //used to bypass the call to the actual TeamRepository:
    private TeamRepository teamRepository;

    static List<Team> teams = new ArrayList<>();

    static {
        teams.add(new Team("FC Milan", "Italy", "Milan", 200_000_000));
        teams.add(new Team("FC Juventus", "Italy", "Turin", 310_000_000));
    }

    @Test
    public void whenValidTeams_thenTeamsShouldBeFound() {
        Mockito.when(teamRepository.findAll()).thenReturn(teams);
        Assertions.assertEquals(teams, teamService.findAllTeams());
    }

    @Test
    public void whenValidTeamId_thenTeamShouldBeFound() {
        Team chelsea = new Team(1, "FC Chelsea", "England", "London", 330_000_000);
        Team liverpool = new Team(2, "FC Liverpool", "England", "Liverpool", 330_000_000);
        int id = 1;
        Optional<Team> teamOptional = Stream.of(chelsea, liverpool).filter(team -> team.getId() == id).findAny();
        Mockito.when(teamRepository.findById(id)).thenReturn(teamOptional);
        System.out.println(teamService.findTeamById(id));
        Assertions.assertEquals(teamOptional, teamService.findTeamById(id));

    }

    @Test
    public void whenValidTeamName_thenTeamShouldBeFound() {
        String name = "FC Milan";
        Mockito.when(teamRepository.findTeamByName(name))
                .thenReturn(teams.stream()
                        .filter(team -> team.getName().equals(name))
                        .collect(Collectors.toList()));
        System.out.println(teamService.findTeamByName(name));
        Assertions.assertEquals(name, teamService.findTeamByName(name).stream().findFirst().get().getName());
    }

    @Test
    public void whenValidTeamCountry_thenTeamShouldBeFound() {
        String country = "Italy";
        Mockito.when(teamRepository.findTeamByCountry(country))
                .thenReturn(teams.stream()
                        .filter(team -> team.getCountry().equals(country))
                        .collect(Collectors.toList()));
        System.out.println(teamService.findTeamByCountry(country));
        Assertions.assertEquals(teams, teamService.findTeamByCountry(country));
    }

    @Test
    public void whenSaveValidTeam_thenTeamShouldBeFound() {
        Team team = new Team("FC Barcelona", "Spain", "Barcelona", 350_000_000);
        Mockito.when(teamRepository.save(team)).thenReturn(team);
        System.out.println(teamService.saveTeam(team));
        Assertions.assertEquals(team, teamService.saveTeam(team));
    }

    @Test
    public void whenValidTeamId_thenTeamShouldBeDeleted() {
        Team team = new Team(1, "FC Chelsea", "England", "London", 330_000_000);
        int teamId = team.getId();
        teamService.deleteTeamById(teamId);
        Mockito.verify(teamRepository, Mockito.times(1)).deleteById(teamId);

    }

}