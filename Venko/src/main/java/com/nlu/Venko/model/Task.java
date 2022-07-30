package com.nlu.Venko.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "isDone")
    private Boolean isDone;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(columnDefinition = "item_id", referencedColumnName = "id")
    private Item item;
}
