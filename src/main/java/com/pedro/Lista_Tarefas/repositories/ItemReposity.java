package com.pedro.Lista_Tarefas.repositories;

import com.pedro.Lista_Tarefas.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemReposity extends JpaRepository<Item,String> {

}
