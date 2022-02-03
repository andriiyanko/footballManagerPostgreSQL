package com.example.andy.footballmanagerpostgre.persistence.dao.repositories;

import com.example.andy.footballmanagerpostgre.persistence.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
@Transactional
public interface PlayerRepository extends CrudRepository<Player, Integer> {
    List<Player> findPlayerByTeamId (Integer teamId);
    List<Player> findPlayerByTeamName (String teamName);
}
