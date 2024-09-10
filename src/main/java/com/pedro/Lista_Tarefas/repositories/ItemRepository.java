package com.pedro.Lista_Tarefas.repositories;

import com.pedro.Lista_Tarefas.models.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,String> {


    List<Item> findByNameContainingAndListItemsIdAndIsActiveTrue(String name, String id);
}
