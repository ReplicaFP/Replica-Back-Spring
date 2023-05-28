package com.salesianas.dam.replica.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "final_project")
@Data
@EqualsAndHashCode(callSuper = false)
public class FinalProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @OneToOne
    private StudentEntity student;

    @Column(name = "title")
    private String title;

    @Column(name = "expositionDate")
    private LocalDate expositionDate;

    //@OneToMany
    //private List<String> progress;
}
