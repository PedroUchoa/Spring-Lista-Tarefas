package com.pedro.Lista_Tarefas.repositories;

import com.pedro.Lista_Tarefas.models.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,String> {

    Page<Item> findByListItemsIdAndIsActiveTrue(String id, Pageable pageable);
}
