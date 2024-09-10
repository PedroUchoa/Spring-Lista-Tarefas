package com.pedro.Lista_Tarefas.dtos;

import com.pedro.Lista_Tarefas.models.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

public record CreateItemDto(
        @NotEmpty(message = "The Name Can't be empty")
        @Size(max = 255, message = "The Name Can't be bigger than 255")
        String name,
        @NotEmpty(message = "The Description Can't be empty")
        @Size(max = 255, message = "The Description Can't be bigger than 255")
        String description,
        @NotNull(message = "The Status Can't be null")
        Status status,
        @NotNull(message = "The Priority Can't be null")
        Boolean priority,
        @NotEmpty(message = "The List id Can't be empty")
        String listId) {
}
