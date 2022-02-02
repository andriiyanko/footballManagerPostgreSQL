package com.example.andy.footballmanagerpostgre.controller;

import com.example.andy.footballmanagerpostgre.exceptions.ResourceNotFoundException;
import com.example.andy.footballmanagerpostgre.persistence.dao.service.interfaces.ITeamService;
import com.example.andy.footballmanagerpostgre.persistence.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
      /*  Optional<Team> teamData = teamService.findTeamById(id);
        if (teamData.isPresent()){
            return new ResponseEntity<>(teamData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }*/

        Team teamData = teamService.findTeamById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Team with id " + id));
        return new ResponseEntity<>(teamData, HttpStatus.OK);

    }

    @PostMapping("/teams")
    public ResponseEntity<Team> createTeam(@Validated @RequestBody Team team){
        try {
            Team _team = teamService.saveTeam(new Team(team.getName(), team.getCountry(), team.getTown(), team.getBalance()));
            return new ResponseEntity<>(_team,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teams/name/{name}")
    public ResponseEntity<List<Team>> getTeamByName(@PathVariable("name") String name){
        try {
            List<Team> teams = teamService.findTeamByName(name);
            if (teams.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(teams, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teams/country/{country}")
    public ResponseEntity<List<Team>> getTeamByCountry(@PathVariable("country") String country){
        try {
            List<Team> teams = teamService.findTeamByCountry(country);
            if (teams.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(teams, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/teams/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable("id") int id, @Validated @RequestBody Team team){
        Team _team = teamService.findTeamById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Team with id " + id));

        _team.setName(team.getName());
        _team.setCountry(team.getCountry());
        _team.setTown(team.getTown());
        _team.setBalance(team.getBalance());

        return new ResponseEntity<>(teamService.saveTeam(_team), HttpStatus.OK);
    }

    @DeleteMapping("/teams/{id}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable("id") int id){
        try {
            teamService.deleteTeamById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/teams")
    public ResponseEntity<HttpStatus> deleteAllTeams(){
        try {
            teamService.deleteAllTeams();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
