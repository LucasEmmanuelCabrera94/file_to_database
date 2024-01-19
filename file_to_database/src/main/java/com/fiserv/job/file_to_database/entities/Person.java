package com.fiserv.job.file_to_database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * This is a class for local use only, for production you should create the corresponding entity.
 *
 */
@Data
@Entity
@Table(name= "person")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "last_name")
    private String lastName;
    private int dni;

    private String createAt;

}

