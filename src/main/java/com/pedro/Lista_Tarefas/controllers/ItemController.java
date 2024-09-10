package com.pedro.Lista_Tarefas.controllers;

import com.pedro.Lista_Tarefas.dtos.CreateItemDto;
import com.pedro.Lista_Tarefas.models.Item;
import com.pedro.Lista_Tarefas.services.ItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/items")
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

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable String id){
        Item item = itemService.getItemById(id);
        return ResponseEntity.ok().body(item);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> desactiveItem(@PathVariable String id){
        itemService.desactiveItem(id);
        return ResponseEntity.noContent().build();
    }



}
