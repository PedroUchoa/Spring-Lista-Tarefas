package com.pedro.Lista_Tarefas.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity(name = "ListItems")
@Table(name = "List")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @CreatedDate
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean active;
    @ManyToOne
    @JoinTable(name = "list_items",
            joinColumns = {@JoinColumn (name = "item_id")},
            inverseJoinColumns = {@JoinColumn(name = "list_id")})
    @JsonManagedReference
    private ListItems listItems;

}
