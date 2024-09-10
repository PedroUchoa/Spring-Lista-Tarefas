package com.pedro.Lista_Tarefas.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreateListDto(
        @NotEmpty(message = "The Name Can't be empty")
        @Size(max = 255, message = "The Name Can't be bigger than 255")
        String name
) {
}
