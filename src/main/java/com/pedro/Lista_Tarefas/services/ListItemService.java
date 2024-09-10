package com.pedro.Lista_Tarefas.services;

import com.pedro.Lista_Tarefas.dtos.CreateItemDto;
import com.pedro.Lista_Tarefas.dtos.CreateListDto;
import com.pedro.Lista_Tarefas.models.ListItems;
import com.pedro.Lista_Tarefas.repositories.ListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListItemService {

    @Autowired
    private ListItemRepository listItemRepository;

    public ListItems createList(CreateListDto list){
        ListItems listItems = new ListItems(list);
        listItemRepository.save(listItems);
        return listItems;
    }

    public ListItems getListById(String id){
        return listItemRepository.getReferenceById(id);
    }

    public Page<ListItems> getAllLists(Pageable pageable){
        return listItemRepository.findAllByIsActiveTrue(pageable);
    }

    public void updateList(CreateListDto list, String id){
        ListItems listItems = listItemRepository.getReferenceById(id);
        listItems.updateList(list);
        listItemRepository.save(listItems);
    }

    public void deleteList(String id){
        ListItems  list =listItemRepository.getReferenceById(id);
        list.desactiveList();
        listItemRepository.save(list);
    }

}
