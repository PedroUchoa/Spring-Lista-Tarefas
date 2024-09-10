package com.pedro.Lista_Tarefas.services;

import com.pedro.Lista_Tarefas.dtos.CreateListDto;
import com.pedro.Lista_Tarefas.dtos.EditItemDto;
import com.pedro.Lista_Tarefas.exceptions.ListNotFoundException;
import com.pedro.Lista_Tarefas.models.Item;
import com.pedro.Lista_Tarefas.models.ListItems;
import com.pedro.Lista_Tarefas.models.Status;
import com.pedro.Lista_Tarefas.repositories.ListItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListItemServiceTest {

    @Mock
    private ListItemRepository listItemRepository;

    @Autowired
    @InjectMocks
    private ListItemService listItemService;

    @Test
    @DisplayName("Should create List Successfully when everything is ok")
    void createListCase1() {
        CreateListDto createListDto = new CreateListDto("teste");

        this.listItemService.createList(createListDto);

        verify(listItemRepository,times(1)).save(any());
    }

    @Test
    @DisplayName("Should Return ListItem By Id Successfully when everything is ok")
    void getListByIdCase1() throws ListNotFoundException {

        ListItems listItems = this.buildListItem();

        when(listItemRepository.findById(listItems.getId())).thenReturn(Optional.of(listItems));

        ListItems listItemsNew = listItemService.getListById(listItems.getId());
        Assertions.assertEquals(listItems,listItemsNew);
        verify(listItemRepository,times(1)).findById(listItems.getId());
    }

    @Test
    @DisplayName("Should thrown Exception when ListItem id Not Found")
    void getListByIdCase2() throws ListNotFoundException {
        Exception thrown = Assertions.assertThrows(ListNotFoundException.class, ()->{
           this.listItemService.getListById("1");
        });

        Assertions.assertEquals("List with Id 1 Not Found in  database", thrown.getMessage());
    }

    @Test
    @DisplayName("Should Return All Lists Successfully when everything is ok")
    void getAllListsCase1() {
        List<ListItems> list = new ArrayList<>();
        list.add(this.buildListItem());
        Page<ListItems> listItemsPage = new PageImpl<>(list);

        when(listItemRepository.findAllByIsActiveTrue(any())).thenReturn(listItemsPage);

        Page<ListItems> resultList = listItemService.getAllLists(any());

        Assertions.assertEquals(1,resultList.getContent().size());
        verify(listItemRepository,times(1)).findAllByIsActiveTrue(any());

    }

    @Test
    @DisplayName("Should Update a List Successfully when everything is ok")
    void updateListCase1() throws ListNotFoundException {
        ListItems listItems = this.buildListItem();
        when(listItemRepository.findById(listItems.getId())).thenReturn(Optional.of(listItems));

        CreateListDto updateList = new CreateListDto("teste2");
        ListItems result = this.listItemService.updateList(updateList, listItems.getId());

        Assertions.assertEquals(result.getName(),updateList.name());
    }

    @Test
    @DisplayName("Should Throw an Exception when List Id Not Found")
    void updateListCase2() throws ListNotFoundException {
        ListItems listItems = this.buildListItem();
        CreateListDto createListDto = new CreateListDto("teste2");

        Exception thrown = Assertions.assertThrows(ListNotFoundException.class, ()->{
            this.listItemService.updateList(createListDto,listItems.getId());
        });

        Assertions.assertEquals("List with Id 1 Not Found in  database", thrown.getMessage());

    }

    @Test
    @DisplayName("Should Delete a List Successfully when everything is ok")
    void deleteListCase1() throws ListNotFoundException {

        ListItems listItems = this.buildListItem();

        when(listItemRepository.findById(listItems.getId())).thenReturn(Optional.of(listItems));
        listItemService.deleteList(listItems.getId());

        Assertions.assertEquals(false,listItems.getIsActive());
    }

    @Test
    @DisplayName("Should Thrown a Exception when List Not Found")
    void deleteListCase2() throws ListNotFoundException {
        ListItems listItems = this.buildListItem();

        Exception thrown = Assertions.assertThrows(ListNotFoundException.class, ()->{
            this.listItemService.deleteList(listItems.getId());
        });

        Assertions.assertEquals("List with Id 1 Not Found in  database", thrown.getMessage());

    }

    private ListItems buildListItem(){
        ListItems listItems = new ListItems();
        listItems.setId("1");
        listItems.setName("teste");
        listItems.setItems(new ArrayList<>());
        return listItems;
    }

}