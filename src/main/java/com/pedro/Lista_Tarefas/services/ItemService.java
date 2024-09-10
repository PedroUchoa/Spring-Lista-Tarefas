package com.pedro.Lista_Tarefas.services;

import com.pedro.Lista_Tarefas.dtos.CreateItemDto;
import com.pedro.Lista_Tarefas.models.Item;
import com.pedro.Lista_Tarefas.repositories.ItemReposity;
import com.pedro.Lista_Tarefas.repositories.ListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemReposity itemReposity;

    @Autowired
    private ListItemRepository listItemRepository;

    public Item createItem(CreateItemDto createItemDto){
        Item item = new Item(createItemDto);
        item.setListItems(listItemRepository.getReferenceById(createItemDto.listId()));
        itemReposity.save(item);
        return item;
    }

    public Item getItemById(String id){
        return itemReposity.getReferenceById(id);
    }

}
