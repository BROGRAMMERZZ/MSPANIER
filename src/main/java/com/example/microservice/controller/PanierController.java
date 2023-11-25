package com.example.microservice.controller;

import com.example.microservice.entities.Item;
import com.example.microservice.entities.Panier;
import com.example.microservice.request.PanierWithItemsRequest;
import com.example.microservice.services.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@EnableEurekaClient
@RequestMapping("/panier")
@RestController
public class PanierController {

    @Autowired
    private PanierService panierService;

    @PostMapping(value = "/createWithItems", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Panier> createPanierWithItems(@RequestBody PanierWithItemsRequest request) {
        Panier panier = request.getPanier();
        List<Item> items = request.getItems();

        Panier createdPanier = panierService.createPanierWithItems(panier, items);

        return new ResponseEntity<>(createdPanier, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Panier> createPanier(@RequestBody Panier panier) {
        return new ResponseEntity<>(panierService.addPanier(panier), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Panier>> getPanier(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(panierService.getPanierById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Panier> updatePanier(@PathVariable(value = "id") int id,
                                               @RequestBody Panier panier) {
        return new ResponseEntity<>(panierService.updatePanier(id, panier), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deletePanier(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(panierService.deletePanier(id), HttpStatus.OK);
    }

    @GetMapping(value = "/getById/{panierId}/item/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Item>> getItemByIdInPanier(
            @PathVariable(value = "panierId") int panierId,
            @PathVariable(value = "itemId") int itemId) {
        return new ResponseEntity<>(panierService.getItemByIdInPanier(panierId, itemId), HttpStatus.OK);
    }

    @PostMapping(value = "/{panierId}/addItems", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Panier> addItemsToPanier(@PathVariable(value = "panierId") int panierId,
                                                   @RequestBody List<Item> items) {
        Panier updatedPanier = panierService.addItemsToPanier(panierId, items);
        return new ResponseEntity<>(updatedPanier, HttpStatus.OK);
    }

}
