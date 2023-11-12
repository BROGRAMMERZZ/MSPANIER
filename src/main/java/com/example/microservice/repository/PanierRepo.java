package com.example.microservice.repository;

import com.example.microservice.entities.Item;
import com.example.microservice.entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PanierRepo extends JpaRepository<Panier,Integer> {
    @Query("SELECT i FROM Panier p JOIN p.items i WHERE p.idPanier = :panierId AND i.idItem = :itemId")
    Optional<Item> findItemByIdInPanier(@Param("panierId") int panierId, @Param("itemId") int itemId);

}
