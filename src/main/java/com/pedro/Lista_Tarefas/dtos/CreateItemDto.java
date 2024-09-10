package com.pedro.Lista_Tarefas.dtos;

import com.pedro.Lista_Tarefas.models.Status;

public record CreateItemDto(String name,String description ,Status status,Boolean priority,String listId) {
}
