package com.pedro.Lista_Tarefas.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "list")
@Entity(name = "List")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListItems {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Boolean Priority;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreatedDate
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean active;

    @OneToMany(mappedBy = "listItems", cascade = CascadeType.PERSIST)
    @JsonBackReference
    private List<Item> items;


}
