package com.example.microservice.controller;

import com.example.microservice.entities.Item;
import com.example.microservice.entities.Panier;
import com.example.microservice.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/item")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        return new ResponseEntity<>(itemService.addItem(item), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Item>> getItem(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(itemService.getItemById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Item> updateItem(@PathVariable(value = "id") int id,
                                           @RequestBody Item newItem) {
        return new ResponseEntity<>(itemService.updateItem(id, newItem), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteItem(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(itemService.deleteItem(id), HttpStatus.OK);
    }

    @GetMapping(value = "/getByPanier", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Item>> getItemsByPanier(@RequestParam Panier panier) {
        return new ResponseEntity<>(itemService.getItemsByPanier(panier), HttpStatus.OK);
    }
}
