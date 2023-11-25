package com.example.microservice.request;

import com.example.microservice.entities.Item;
import com.example.microservice.entities.Panier;
import lombok.Data;

import java.util.List;

@Data
public class PanierWithItemsRequest {

    private Panier panier;
    private List<Item> items;

}