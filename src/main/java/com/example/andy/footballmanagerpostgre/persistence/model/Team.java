package com.example.andy.footballmanagerpostgre.persistence.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String country;
    private String town;
    private int balance;

    public Team(String name, String country, String town, int balance) {
        this.name = name;
        this.country = country;
        this.town = town;
        this.balance = balance;
    }
}
