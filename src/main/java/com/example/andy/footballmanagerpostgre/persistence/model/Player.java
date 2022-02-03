package com.example.andy.footballmanagerpostgre.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "player")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "first name must not be empty")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "last name must not be empty")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "birth date must not be empty. Date format yyyy-mm-dd")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotNull(message = "start career date must not be empty. Date format yyyy-mm-dd")
    @Column(name = "start_career")
    private LocalDate startCareer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    @JsonBackReference
    private Team team;




}
