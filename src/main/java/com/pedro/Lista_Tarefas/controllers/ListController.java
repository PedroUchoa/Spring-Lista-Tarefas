package com.pedro.Lista_Tarefas.controllers;

import com.pedro.Lista_Tarefas.dtos.CreateListDto;
import com.pedro.Lista_Tarefas.exceptions.ListNotFoundException;
import com.pedro.Lista_Tarefas.models.ListItems;
import com.pedro.Lista_Tarefas.services.ListItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/list")
public class ListController {

    @Autowired
    private ListItemService listItemService;

    @PostMapping
    @Transactional
    public ResponseEntity<CreateListDto> createList(@RequestBody CreateListDto createListDto, UriComponentsBuilder uriBuilder){
        ListItems listItems = listItemService.createList(createListDto);
        URI uri = uriBuilder.path("/list/{id}").buildAndExpand(listItems.getId()).toUri();
        return ResponseEntity.created(uri).body(createListDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListItems> getListById(@PathVariable String id) throws ListNotFoundException {
        ListItems listItems = listItemService.getListById(id);
        return ResponseEntity.ok().body(listItems);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ListItems>> getAllLists(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable){
        Page<ListItems> listItems = listItemService.getAllLists(pageable);
        return ResponseEntity.ok().body(listItems);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateList(@RequestBody CreateListDto dto, @PathVariable String id) throws ListNotFoundException {
        listItemService.updateList(dto,id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable String id) throws ListNotFoundException {
        listItemService.deleteList(id);
        return ResponseEntity.noContent().build();
    }

}


