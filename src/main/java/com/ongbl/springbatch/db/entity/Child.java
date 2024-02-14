package com.ongbl.springbatch.db.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "children")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

}
