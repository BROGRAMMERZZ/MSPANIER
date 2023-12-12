package com.example.microservice.controller;

import com.example.microservice.entities.Item;
import com.example.microservice.entities.Panier;
import com.example.microservice.repository.PanierRepo;
import com.example.microservice.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@EnableEurekaClient
@RequestMapping("/itemRestController")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private PanierRepo panierRepo;

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item newItem = itemService.addItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @GetMapping("/{idItem}")
    public ResponseEntity<Item> getItemById(@PathVariable int idItem) {
        Optional<Item> item = itemService.getItemById(idItem);
        return item.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/findall/{idPanier}")
    public ResponseEntity<List<Item>> findAllByPanier(@PathVariable int idPanier) {
        Panier panier = panierRepo.findByIdPanier(idPanier);
        List<Item> items = itemService.getItemsByPanier(panier);
        return items.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PutMapping("/{idItem}")
    public ResponseEntity<Item> updateItem(@PathVariable int idItem,
                                           @RequestBody Item item) {
        Item updatedItem = itemService.updateItem(idItem, item);
        return updatedItem != null
                ? new ResponseEntity<>(updatedItem, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{idItem}")
    public ResponseEntity<String> deleteItem(@PathVariable int idItem) {
        String result = itemService.deleteItem(idItem);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}


