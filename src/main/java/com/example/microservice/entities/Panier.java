package com.example.microservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;



@Getter
@Setter
@Entity
public class Panier {
    @Id
    @GeneratedValue
    private int idPanier;
    private int idUser;
    private double prixtotal;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL)
    private Set<Item> items;

}
