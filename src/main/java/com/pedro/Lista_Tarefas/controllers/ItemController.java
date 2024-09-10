package com.pedro.Lista_Tarefas.controllers;

import com.pedro.Lista_Tarefas.dtos.CreateItemDto;
import com.pedro.Lista_Tarefas.models.Item;
import com.pedro.Lista_Tarefas.services.ItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    @Transactional
    public ResponseEntity<Item> createItem(@RequestBody CreateItemDto createItemDto, UriComponentsBuilder uriComponentsBuilder){
        Item item =itemService.createItem(createItemDto);
        URI uri = uriComponentsBuilder.path("/item/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(item);
    }

}
