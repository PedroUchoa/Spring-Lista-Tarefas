package com.pedro.Lista_Tarefas.services;

import com.pedro.Lista_Tarefas.dtos.CreateListDto;
import com.pedro.Lista_Tarefas.models.ListItems;
import com.pedro.Lista_Tarefas.repositories.ListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

}
