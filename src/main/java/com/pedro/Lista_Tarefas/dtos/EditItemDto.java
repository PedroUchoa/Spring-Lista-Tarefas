package com.pedro.Lista_Tarefas.dtos;

import com.pedro.Lista_Tarefas.models.Status;

public record EditItemDto(String name, String description, Status status) {
}
