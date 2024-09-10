package com.pedro.Lista_Tarefas.services;

import com.pedro.Lista_Tarefas.repositories.ItemReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemReposity itemReposity;
}
