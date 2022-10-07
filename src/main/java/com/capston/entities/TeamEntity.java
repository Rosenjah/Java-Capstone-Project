package com.capston.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long parentId;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column
    private String address;

    @OneToMany
    private List<KidEntity> kidEntities;



}
