package com.example.andy.footballmanagerpostgre.controller;

import com.example.andy.footballmanagerpostgre.exceptions.ResourceNotFoundException;
import com.example.andy.footballmanagerpostgre.persistence.dao.service.interfaces.IPlayerService;
import com.example.andy.footballmanagerpostgre.persistence.dao.service.interfaces.ITeamService;
import com.example.andy.footballmanagerpostgre.persistence.model.Player;
import com.example.andy.footballmanagerpostgre.persistence.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController {

    private ITeamService teamService;
    private IPlayerService playerService;

    @Autowired
    public void setTeamService(ITeamService teamService) {
        this.teamService = teamService;
    }

    @Autowired
    public void setPlayerService(IPlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/teams/{teamId}/players")
    public ResponseEntity<List<Player>> getAllPLayersByTeamId(@PathVariable("teamId") Integer teamId){
        teamService.findTeamById(teamId).orElseThrow(() -> new ResourceNotFoundException("Not found Team by id " + teamId));

        try {
            List<Player> players = playerService.findPlayerByTeamId(teamId);
            return new ResponseEntity<>(players, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teams/name/{teamName}/players")
    public ResponseEntity<List<Player>> getAllPlayersByTeamName(@PathVariable("teamName") String teamName){

        try {
            List<Team> teams = teamService.findTeamByName(teamName);
            if (teams.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<Player> players = playerService.findPlayerByTeamName(teamName);
            return new ResponseEntity<>(players,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/players")
    public ResponseEntity<List<Player>> getAllPlayers(){
        try {
            List<Player> players = new ArrayList<>();
            playerService.findAllPLayers().forEach(players::add);
            if (players.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(players, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/players/{firstName}/{lastName}")
    public ResponseEntity<List<Player>> getPlayersByFirstNameAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
        try {
            List<Player> players = playerService.findPlayerByFirstNameAndLastName(firstName, lastName);
            if (players.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(players,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/teams/{teamId}/players")
    public ResponseEntity<Player> createPlayer (@PathVariable("teamId") Integer teamId, @RequestBody Player playerRequest){
        Player player = teamService.findTeamById(teamId).map(team -> {
            playerRequest.setTeam(team);
            return playerService.savePLayer(playerRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Team by id" + teamId));

        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }
}
