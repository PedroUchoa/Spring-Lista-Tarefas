package com.pedro.Lista_Tarefas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason ="List not found by this ID")
public class ListNotFoundException extends Exception{

    public ListNotFoundException(String id){
        super("List with Id " + id +" Not Found in  database");
    }

}
