package com.fiserv.job.file_to_database.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * This is a class for local use only, for production you should create the corresponding entity.
 *
 */
@Data
@Entity
@Table(name= "person")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "last_name")
    private String lastName;
    private int dni;

    private String createAt;

    public Person(String name, String lastName, int dni) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
    }

    public Person() {
    }
}

