package com.pedro.Lista_Tarefas.services;

import com.pedro.Lista_Tarefas.dtos.CreateItemDto;
import com.pedro.Lista_Tarefas.models.Item;
import com.pedro.Lista_Tarefas.repositories.ItemRepository;
import com.pedro.Lista_Tarefas.repositories.ListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


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

    public Page<Item> getAllActives(String id, Pageable pageable){
        return itemRepository.findByListItemsIdAndIsActiveTrue(id,pageable);

    }

    public void desactiveItem(String id) {
        Item item = itemRepository.getReferenceById(id);
        item.desactiveItem();
        itemRepository.save(item);
    }
}
