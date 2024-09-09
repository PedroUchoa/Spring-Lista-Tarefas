package com.pedro.Lista_Tarefas.repositories;

import com.pedro.Lista_Tarefas.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemReposity extends JpaRepository<Item,String> {
}
