package com.example.andy.footballmanagerpostgre.persistence.dao.repositories;

import com.example.andy.footballmanagerpostgre.persistence.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TeamRepository extends CrudRepository<Team,Integer> {
    List<Team> findTeamByName (String name);
    List<Team> findTeamByCountry (String country);
}
