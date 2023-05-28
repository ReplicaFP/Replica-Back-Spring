package com.salesianas.dam.replica.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.salesianas.dam.replica.persistence.entity.EmployeeEntity;
import com.salesianas.dam.replica.persistence.entity.StudentEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalTime;

public class InternshipRest {


    @JsonProperty("student")
    private StudentEntity student;

    @JsonProperty("employee")
    private EmployeeEntity employee;

    @JsonProperty("startingDate")
    private LocalTime startingDate;

    @JsonProperty("endingDate")
    private LocalTime endingDate;

    @JsonProperty("type")
    private InternshipType type;

    @JsonProperty("totalHours")
    private Integer totalHours;
}
