package com.example.andy.footballmanagerpostgre.persistence.dao.service.implementation;

import com.example.andy.footballmanagerpostgre.persistence.dao.repositories.PlayerRepository;
import com.example.andy.footballmanagerpostgre.persistence.dao.service.interfaces.IPlayerService;
import com.example.andy.footballmanagerpostgre.persistence.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements IPlayerService {

    private PlayerRepository playerRepository;

    @Autowired
    public void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
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
}
