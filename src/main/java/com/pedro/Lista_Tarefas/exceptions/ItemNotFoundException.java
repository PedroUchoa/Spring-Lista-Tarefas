package com.pedro.Lista_Tarefas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "item not found in database")
public class ItemNotFoundException extends Exception{

    public ItemNotFoundException(String id){
        super("Item with id: " + id + " not found");
    }

}
