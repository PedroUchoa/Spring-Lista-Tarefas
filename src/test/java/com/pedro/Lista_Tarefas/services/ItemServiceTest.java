package com.pedro.Lista_Tarefas.services;

import com.pedro.Lista_Tarefas.dtos.CreateItemDto;
import com.pedro.Lista_Tarefas.dtos.EditItemDto;
import com.pedro.Lista_Tarefas.exceptions.ItemNotFoundException;
import com.pedro.Lista_Tarefas.exceptions.ListNotFoundException;
import com.pedro.Lista_Tarefas.models.Item;
import com.pedro.Lista_Tarefas.models.ListItems;
import com.pedro.Lista_Tarefas.models.Status;
import com.pedro.Lista_Tarefas.repositories.ItemRepository;
import com.pedro.Lista_Tarefas.repositories.ListItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ListItemRepository listItemRepository;

    @Autowired
    @InjectMocks
    private ItemService itemService;

    @Test
    @DisplayName("Should create Item Successfully when everything is ok")
    void createItemCase1() throws ListNotFoundException {
        CreateItemDto createItemDto = new CreateItemDto("teste","teste", Status.PENDENTE,true,"1");
        ListItems listItems = this.buildListItem();

        when(listItemRepository.findById("1")).thenReturn(Optional.of(listItems));

        this.itemService.createItem(createItemDto);
        verify(itemRepository,times(1)).save(any());
    }

    @Test
    @DisplayName("Should thrown Exception when List id Not Found")
    void createItemCase2() throws ListNotFoundException {
        CreateItemDto createItemDto = new CreateItemDto("teste","teste", Status.PENDENTE,true,"1");

        Exception thrown = Assertions.assertThrows(ListNotFoundException.class, ()->{
            this.itemService.createItem(createItemDto);
        });
        Assertions.assertEquals("List with Id 1 Not Found in  database",thrown.getMessage());
    }

    @Test
    @DisplayName("Should Return Item By Id")
    void getItemByIdCase1() throws ItemNotFoundException {
        Item item = this.buildItem();

        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));

        Item itemTest = itemService.getItemById(item.getId());

        Assertions.assertEquals(item,itemTest);
        verify(itemRepository,times(1)).findById(item.getId());
    }

    @Test
    @DisplayName("Should Thrown Exception When Item Id Not Found")
    void getItemByIdCase2() throws ItemNotFoundException {
        Item item = this.buildItem();

        Exception thrown = Assertions.assertThrows(ItemNotFoundException.class, ()->{
            this.itemService.getItemById(item.getId());
        });
        Assertions.assertEquals("Item with id: 1 not found",thrown.getMessage());
    }

    @Test
    @DisplayName("Should Return Item By Name")
    void getByNameCase1() {
        Item item = this.buildItem();
        ListItems listItems = this.buildListItem();
        listItems.getItems().add(item);

        when(itemRepository.findByNameContainingAndListItemsIdAndIsActiveTrue(item.getName(),listItems.getId())).thenReturn(listItems.getItems());

        List<Item> itemTest = itemService.getByName(item.getName(),listItems.getId());

        Assertions.assertEquals(1,itemTest.size());
        verify(itemRepository,times(1)).findByNameContainingAndListItemsIdAndIsActiveTrue(item.getName(),listItems.getId());

    }

    @Test
    @DisplayName("Should Update an Item")
    void updateItemCase1() throws ItemNotFoundException {
        Item item = this.buildItem();
        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));

        EditItemDto updateItem = new EditItemDto("teste2","teste2",Status.FEITO);
        Item result = this.itemService.updateItem(updateItem,item.getId());

        Assertions.assertEquals(result.getName(),updateItem.name());
        Assertions.assertEquals(result.getDescription(),updateItem.description());
        Assertions.assertEquals(result.getStatus(),updateItem.status());
    }

    @Test
    @DisplayName("Should Thrown Exception For Not Founding Item")
    void updateItemCase2() throws ItemNotFoundException {
        Item item = this.buildItem();
        EditItemDto updateItem = new EditItemDto("teste2","teste2",Status.FEITO);

        Exception thrown = Assertions.assertThrows(ItemNotFoundException.class, ()->{
            this.itemService.updateItem(updateItem,item.getId());
        });
        Assertions.assertEquals("Item with id: 1 not found",thrown.getMessage());
    }


    @Test
    @DisplayName("Should Desactive an Item")
    void desactiveItemCase1() throws ItemNotFoundException {
        Item item = this.buildItem();

        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));
        itemService.desactiveItem(item.getId());

        Assertions.assertEquals(false,item.getIsActive());

    }


    @Test
    @DisplayName("Should Thrown an Exception When Item Not Found")
    void desactiveItemCase2() throws ItemNotFoundException {
        Item item = this.buildItem();

        Exception thrown = Assertions.assertThrows(ItemNotFoundException.class, ()->{
            this.itemService.desactiveItem(item.getId());
        });
        Assertions.assertEquals("Item with id: 1 not found",thrown.getMessage());

    }


    private Item buildItem(){
        Item item = new Item();
        item.setId("1");
        item.setName("teste");
        item.setDescription("teste");
        item.setStatus(Status.PENDENTE);
        item.setPriority(true);
        return item;
    }

    private ListItems buildListItem(){
        ListItems listItems = new ListItems();
        listItems.setId("1");
        listItems.setName("teste");
        listItems.setItems(new ArrayList<>());
        return listItems;
    }



}