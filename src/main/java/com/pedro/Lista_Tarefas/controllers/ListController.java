package com.pedro.Lista_Tarefas.controllers;

import com.pedro.Lista_Tarefas.CreateListDto;
import com.pedro.Lista_Tarefas.models.ListItems;
import com.pedro.Lista_Tarefas.services.ListItemService;
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

}
