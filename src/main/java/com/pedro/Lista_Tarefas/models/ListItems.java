package com.pedro.Lista_Tarefas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "list")
@Entity(name = "List")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListItems {

    private String id;
    private String name;
    private Boolean Priority;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean active;

}
