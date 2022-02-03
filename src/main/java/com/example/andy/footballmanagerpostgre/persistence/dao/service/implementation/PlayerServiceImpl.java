package com.example.andy.footballmanagerpostgre.persistence.dao.service.implementation;

import com.example.andy.footballmanagerpostgre.persistence.dao.repositories.PlayerRepository;
import com.example.andy.footballmanagerpostgre.persistence.dao.service.interfaces.IPlayerService;
import com.example.andy.footballmanagerpostgre.persistence.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements IPlayerService {

    private PlayerRepository playerRepository;

    @Autowired
    public void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Optional<Player> findPlayerById(Integer id) {
        return playerRepository.findById(id);
    }

    @Override
    public List<Player> findPlayerByTeamId(Integer teamId) {
        return playerRepository.findPlayerByTeamId(teamId);
    }

    @Override
    public List<Player> findPlayerByTeamName(String teamName) {
        return playerRepository.findPlayerByTeamName(teamName);
    }

    @Override
    public Iterable<Player> findAllPLayers() {
        return playerRepository.findAll();
    }

    @Override
    public List<Player> findPlayerByFirstNameAndLastName(String firstName, String lastName) {
        return playerRepository.findPlayerByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Player savePLayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void deletePlayerById(Integer id) {
        playerRepository.deleteById(id);
    }

    @Override
    public void deleteAllPlayers() {
        playerRepository.deleteAll();
    }
}
