package com.example.andy.footballmanagerpostgre.persistence.dao.service.implementation;

import com.example.andy.footballmanagerpostgre.persistence.dao.repositories.PlayerRepository;
import com.example.andy.footballmanagerpostgre.persistence.dao.service.interfaces.IPlayerService;
import com.example.andy.footballmanagerpostgre.persistence.model.Player;
import com.example.andy.footballmanagerpostgre.persistence.model.Team;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlayerServiceImplTest {

    @TestConfiguration
    static class PlayersServiceImplTestContextConfiguration{
        @Bean
        public IPlayerService playerService(){
            return new PlayerServiceImpl();
        }
    }

    @Autowired
    private IPlayerService playerService;

    @MockBean
    private PlayerRepository playerRepository;

    static List<Player> players = new ArrayList<>();

    static {
        players.add(new Player(1, "Lionel", "Messi", LocalDate.of(1985,07,11), LocalDate.of(2005,05,20), new Team(1,"FC Barcelona", "Spain", "Barcelona", 300_000_000)));
        players.add(new Player(2, "Cristian", "Ronaldo", LocalDate.of(1985,10,15), LocalDate.of(2006,04,20), new Team(2,"FC Real Madrid", "Spain", "Madrid", 300_000_000)));
    }

    @Test
    public void whenValidPlayers_thenPlayersShouldBeFound() {
        Mockito.when(playerRepository.findAll()).thenReturn(players);
        Assertions.assertEquals(players, playerService.findAllPLayers());
    }

    @Test
    public void whenValidPlayerId_thenPlayerShouldBeFound() {
        int playerId = players.stream().findFirst().get().getId();
        Optional<Player> playerOptional = players.stream().filter(player -> player.getId() == playerId).findAny();
        Mockito.when(playerRepository.findById(playerId)).thenReturn(playerOptional);
        System.out.println(playerService.findPlayerById(playerId));
        Assertions.assertEquals(playerOptional, playerService.findPlayerById(playerId));
    }

    @Test
    public void whenValidPlayerFirstAndLastName_thenPlayerShouldBeFound() {
        String firstName = "Lionel";
        String lastName = "Messi";
        Mockito.when(playerRepository.findPlayerByFirstNameAndLastName(firstName, lastName)).thenReturn(players.stream()
                .filter(player -> player.getFirstName().equals(firstName))
                .filter(player -> player.getLastName().equals(lastName))
                .collect(Collectors.toList()));

        System.out.println(playerService.findPlayerByFirstNameAndLastName(firstName, lastName));
        Assertions.assertEquals(firstName, playerService.findPlayerByFirstNameAndLastName(firstName,lastName).stream().findFirst().get().getFirstName());
        Assertions.assertEquals(lastName, playerService.findPlayerByFirstNameAndLastName(firstName,lastName).stream().findFirst().get().getLastName());

    }

    @Test
    public void whenSaveValidPlayer_thenPlayerShouldBeFound() {
        Player player = new Player(1, "Lionel", "Messi", LocalDate.of(1985,07,11), LocalDate.of(2005,05,20));
        Mockito.when(playerRepository.save(player)).thenReturn(player);
        System.out.println(playerService.savePLayer(player));
        Assertions.assertEquals(player, playerService.savePLayer(player));
    }

    @Test

    public void whenValidTeamId_thenPlayersShouldBeFound() {
        players.add(new Player(1, "Gerard", "Pique", LocalDate.of(1985,07,11), LocalDate.of(2005,05,20), new Team(1,"FC Barcelona", "Spain", "Barcelona", 300_000_000)));
        int teamId = 1;
        Mockito.when(playerRepository.findPlayerByTeamId(teamId)).thenReturn(players.stream()
                .filter(player -> player.getTeam().getId() == teamId)
                .collect(Collectors.toList()));
        System.out.println(playerService.findPlayerByTeamId(teamId));
        Assertions.assertEquals(players.stream().filter(player -> player.getTeam().getId() == teamId).findFirst().get(), playerService.findPlayerByTeamId(teamId).get(0));

    }

    @Test
    public void whenValidTeamName_thenPlayersShouldBeFound() {
        String teamName = "FC Barcelona";
        Mockito.when(playerRepository.findPlayerByTeamName(teamName)).thenReturn(players.stream()
                .filter(player -> player.getTeam().getName().equals(teamName))
                .collect(Collectors.toList()));
        System.out.println(playerService.findPlayerByTeamName(teamName));
        Assertions.assertEquals(teamName, playerService.findPlayerByTeamName(teamName).stream().findFirst().get().getTeam().getName());
    }

    @Test
    public void whenValidPlayerId_thenPlayerShouldBeDeleted() {
        int playerId = players.get(0).getId();
        playerService.deletePlayerById(playerId);
        Mockito.verify(playerRepository, Mockito.times(1)).deleteById(playerId);
    }
}