package com.pedro.Lista_Tarefas.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pedro.Lista_Tarefas.dtos.CreateItemDto;
import com.pedro.Lista_Tarefas.dtos.EditItemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "Item")
@Table(name = "items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private LocalDateTime startDate = LocalDateTime.now();
    private LocalDateTime endDate;
    private Boolean isActive=true;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Boolean priority;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinTable(name = "list_items",
            joinColumns = {@JoinColumn (name = "items_id")},
            inverseJoinColumns = {@JoinColumn(name = "list_id")})
    @JsonBackReference
    private ListItems listItems;

    public Item(CreateItemDto createItemDto) {
        this.name = createItemDto.name();
        this.description = createItemDto.description();
        this.status = createItemDto.status();
        this.priority=createItemDto.priority();
    }

    public void desactiveItem() {
        this.isActive = false;
        this.endDate = LocalDateTime.now();
    }

    public void updateItem(EditItemDto itemDto) {
        if(itemDto.name() != null && !itemDto.name().equals(" ")) this.name = itemDto.name();

        if(itemDto.description() != null && !itemDto.description().equals(" ")) this.description = itemDto.description();

        if(itemDto.status() != null) this.status = itemDto.status();
    }
}
