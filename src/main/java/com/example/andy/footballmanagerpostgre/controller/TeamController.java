package com.example.andy.footballmanagerpostgre.controller;

import com.example.andy.footballmanagerpostgre.persistence.dao.service.interfaces.ITeamService;
import com.example.andy.footballmanagerpostgre.persistence.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TeamController {

    private ITeamService teamService;

    @Autowired
    public void setTeamService(ITeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getAllTeams(){
        try {
            List<Team> teams = new ArrayList<>();
            teamService.findAllTeams().forEach(teams::add);
            if (teams.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(teams,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable("id") int id){
        Optional<Team> teamData = teamService.findTeamById(id);
        if (teamData.isPresent()){
            return new ResponseEntity<>(teamData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/teams")
    public ResponseEntity<Team> createTeam(HttpServletRequest httpServletRequest, @RequestBody Team team){
        try {
            Team _team = teamService.saveTeam(new Team(team.getName(), team.getCountry(), team.getTown(), team.getBalance()));
            return new ResponseEntity<>(_team,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
