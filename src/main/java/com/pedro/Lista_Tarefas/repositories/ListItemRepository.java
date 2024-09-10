package com.pedro.Lista_Tarefas.repositories;

import com.pedro.Lista_Tarefas.models.ListItems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListItemRepository extends JpaRepository<ListItems,String> {

    Page<ListItems> findAllByIsActiveTrue(Pageable pageable);
}
