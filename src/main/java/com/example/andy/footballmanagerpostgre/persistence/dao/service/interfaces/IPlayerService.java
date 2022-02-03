package com.example.andy.footballmanagerpostgre.persistence.dao.service.interfaces;

import com.example.andy.footballmanagerpostgre.persistence.model.Player;

import java.util.List;

public interface IPlayerService {
    List<Player> findPlayerByTeamId(Integer teamId);
    List<Player> findPlayerByTeamName (String teamName);
    Iterable<Player> findAllPLayers();
    List<Player> findPlayerByFirstNameAndLastName (String firstName, String lastName);
    Player savePLayer (Player player);

}
