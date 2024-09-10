package com.pedro.Lista_Tarefas.services;

import com.pedro.Lista_Tarefas.dtos.CreateListDto;
import com.pedro.Lista_Tarefas.models.Item;
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
        ListItems listItems= listItemRepository.getReferenceById(id);
        listItems.setItems(listItems.getItems().stream().filter(Item::getIsActive).toList());
        return listItems;
    }

    public Page<ListItems> getAllLists(Pageable pageable){
        Page<ListItems> list = listItemRepository.findAllByIsActiveTrue(pageable);
        list.getContent().forEach(x->x.setItems(x.getItems().stream().filter(Item::getIsActive).toList()));
        return list;
    }

    public void updateList(CreateListDto list, String id){
        ListItems listItems = listItemRepository.getReferenceById(id);
        listItems.updateList(list);
        listItemRepository.save(listItems);
    }

    public void deleteList(String id){
        ListItems  list =listItemRepository.getReferenceById(id);
        listItemRepository.delete(list);
    }

}
