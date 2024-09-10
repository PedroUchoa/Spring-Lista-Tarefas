package com.pedro.Lista_Tarefas.services;

import com.pedro.Lista_Tarefas.dtos.CreateItemDto;
import com.pedro.Lista_Tarefas.dtos.EditItemDto;
import com.pedro.Lista_Tarefas.models.Item;
import com.pedro.Lista_Tarefas.repositories.ItemRepository;
import com.pedro.Lista_Tarefas.repositories.ListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ListItemRepository listItemRepository;

    public Item createItem(CreateItemDto createItemDto){
        Item item = new Item(createItemDto);
        item.setListItems(listItemRepository.getReferenceById(createItemDto.listId()));
        itemRepository.save(item);
        return item;
    }

    public Item getItemById(String id){
        return itemRepository.getReferenceById(id);
    }

    public List<Item> getByName(String name, String id){
        System.out.println(name);
        return itemRepository.findByNameContainingAndListItemsIdAndIsActiveTrue(name,id);
    }

    public void updateItem(EditItemDto itemDto, String id){
        Item item = itemRepository.getReferenceById(id);
        item.updateItem(itemDto);
        itemRepository.save(item);
    }

    public void desactiveItem(String id) {
        Item item = itemRepository.getReferenceById(id);
        item.desactiveItem();
        itemRepository.save(item);
    }
}
