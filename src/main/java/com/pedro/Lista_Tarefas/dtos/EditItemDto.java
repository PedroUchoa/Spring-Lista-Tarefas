package com.pedro.Lista_Tarefas.dtos;

import com.pedro.Lista_Tarefas.models.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record EditItemDto(
        @NotEmpty(message = "The Name Can't be empty")
        @Size(max = 255, message = "The Name Can't be bigger than 255")
        String name,
        @NotEmpty(message = "The Description Can't be empty")
        @Size(max = 255, message = "The Description Can't be bigger than 255")
        String description,
        @NotEmpty(message = "The Status Can't be empty")
        Status status) {
}
