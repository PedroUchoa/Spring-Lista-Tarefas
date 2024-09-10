package com.pedro.Lista_Tarefas.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pedro.Lista_Tarefas.dtos.CreateListDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "list")
@Entity(name = "ListItems")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListItems {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private LocalDateTime startDate = LocalDateTime.now();
    private LocalDateTime endDate;
    private Boolean isActive = true;

    @OneToMany(mappedBy = "listItems", cascade = CascadeType.PERSIST)
    @JsonBackReference
    private List<Item> items = new ArrayList<>();


    public ListItems(CreateListDto list) {
        this.name = list.name();
    }
}
