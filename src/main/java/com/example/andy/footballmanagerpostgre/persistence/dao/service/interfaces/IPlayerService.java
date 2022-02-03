package com.example.andy.footballmanagerpostgre.persistence.dao.service.interfaces;

import com.example.andy.footballmanagerpostgre.persistence.model.Player;

import java.util.List;
import java.util.Optional;

public interface IPlayerService {
    List<Player> findPlayerByTeamId(Integer teamId);
    List<Player> findPlayerByTeamName (String teamName);
    Iterable<Player> findAllPLayers();
    List<Player> findPlayerByFirstNameAndLastName (String firstName, String lastName);
    Player savePLayer (Player player);
    Optional<Player> findPlayerById (Integer id);

    void deletePlayerById(Integer id);
    void deleteAllPlayers();

}
