package com.pedro.Lista_Tarefas.controllers;

import com.pedro.Lista_Tarefas.dtos.CreateItemDto;
import com.pedro.Lista_Tarefas.dtos.EditItemDto;
import com.pedro.Lista_Tarefas.dtos.UpdateItemDto;
import com.pedro.Lista_Tarefas.models.Item;
import com.pedro.Lista_Tarefas.services.ItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @GetMapping("/{listId}/search")
    public ResponseEntity<List<Item>> getItemByName(@RequestParam String name, @PathVariable String listId){
        List<Item> items = itemService.getByName(name,listId);
        return ResponseEntity.ok().body(items);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Void> updateItem(@PathVariable String id, @RequestBody EditItemDto itemDto){
        itemService.updateItem(itemDto,id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> desactiveItem(@PathVariable String id){
        itemService.desactiveItem(id);
        return ResponseEntity.noContent().build();
    }



}
