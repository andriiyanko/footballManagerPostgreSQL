package com.example.andy.footballmanagerpostgre.controller;

import com.example.andy.footballmanagerpostgre.persistence.dao.service.interfaces.ITeamService;
import com.example.andy.footballmanagerpostgre.persistence.model.Team;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(TeamController.class) //to test controllers
public class TeamControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean // provide implementation
    private ITeamService teamService;

    @Autowired
    private ObjectMapper mapper;

    static List<Team> teams = new ArrayList<>();

    static {
        teams.add(new Team(1,"FC Milan", "Italy", "Milan", 200_000_000));
        teams.add(new Team(2,"FC Juventus", "Italy", "Turin", 310_000_000));
    }

    @Test
    public void givenTeams_thenReturnTeamList() {
        try {
            Mockito.when(teamService.findAllTeams()).thenReturn(teams);
            System.out.println(teamService.findAllTeams());
            mvc.perform(get("/api/teams"))
                    .andExpect(status().isOk())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenTeamId_thenReturnTeam() {
        int id = 1;
        try {
            Optional<Team> teamOptional = teams.stream().filter(team -> team.getId() == id).findFirst();
            Mockito.when(teamService.findTeamById(id)).thenReturn(teamOptional);
            mvc.perform(get("/api/teams/{id}", id))
                    .andExpect(status().isOk())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenTeam_SaveAndThenReturnTeam() {
        try {
            Team team = new Team(1,"FC Barcelona", "Spain", "Barcelona", 350_000_000);
            Mockito.when(teamService.saveTeam(team)).thenReturn(team);
            System.out.println(teamService.saveTeam(team));

            mvc.perform(post("/api/teams")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(team))
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated()).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenTeamName_thenReturnTeam() {
        String teamName = "FC Milan";
        try {
            Mockito.when(teamService.findTeamByName(teamName))
                    .thenReturn(teams.stream()
                            .filter(team -> team.getName().equals(teamName))
                            .collect(Collectors.toList()));
            System.out.println(teamService.findTeamByName(teamName));
            mvc.perform(get("/api/teams/name/{name}",teamName))
                    .andExpect(status().isOk())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenTeamCountry_thenReturnTeam() {
        String country = "Italy";
        try {
            Mockito.when(teamService.findTeamByCountry(country))
                    .thenReturn(teams.stream()
                            .filter(team -> team.getCountry().equals(country))
                            .collect(Collectors.toList()));
            System.out.println(teamService.findTeamByName(country));
            mvc.perform(get("/api/teams/country/{country}",country))
                    .andExpect(status().isOk())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}