package com.pedro.Lista_Tarefas.services;

import com.pedro.Lista_Tarefas.dtos.CreateItemDto;
import com.pedro.Lista_Tarefas.dtos.EditItemDto;
import com.pedro.Lista_Tarefas.exceptions.ItemNotFoundException;
import com.pedro.Lista_Tarefas.exceptions.ListNotFoundException;
import com.pedro.Lista_Tarefas.models.Item;
import com.pedro.Lista_Tarefas.models.ListItems;
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

    public Item createItem(CreateItemDto createItemDto) throws ListNotFoundException {
        Item item = new Item(createItemDto);
        ListItems list = listItemRepository.findById(createItemDto.listId()).orElseThrow(()->new ListNotFoundException(createItemDto.listId()));
        item.setListItems(list);
        itemRepository.save(item);
        return item;
    }

    public Item getItemById(String id) throws ItemNotFoundException {
        return itemRepository.findById(id).orElseThrow(()->new ItemNotFoundException(id));
    }

    public List<Item> getByName(String name, String id){
        return itemRepository.findByNameContainingAndListItemsIdAndIsActiveTrue(name,id);
    }

    public Item updateItem(EditItemDto itemDto, String id) throws ItemNotFoundException {
        Item item = itemRepository.findById(id).orElseThrow(()->new ItemNotFoundException(id));
        item.updateItem(itemDto);
        itemRepository.save(item);
        return item;
    }

    public void desactiveItem(String id) throws ItemNotFoundException {
        Item item = itemRepository.findById(id).orElseThrow(()->new ItemNotFoundException(id));
        if (!item.getIsActive()) throw new ItemNotFoundException(id);
        item.desactiveItem();
        itemRepository.save(item);
    }
}
