package com.example.andy.footballmanagerpostgre.persistence.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Size(min = 7, message = "team name should have at least 7 characters")
    private String name;

    @NotEmpty
    @Size(min = 3, message = "team country should have at least 3 characters")
    private String country;

    @NotEmpty
    @Size(min = 2, message = "team town should have at least 2 characters")
    private String town;

    @NotNull(message = "team balance must be bigger or equal 10 million")
    @Min(10_000_000)
    private int balance;

    public Team(String name, String country, String town, int balance) {
        this.name = name;
        this.country = country;
        this.town = town;
        this.balance = balance;
    }
}
