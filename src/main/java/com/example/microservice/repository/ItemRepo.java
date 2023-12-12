package com.example.microservice.repository;

import com.example.microservice.entities.Item;
import com.example.microservice.entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item,Integer> {
    List<Item> findByPanier(Panier panier);

}
