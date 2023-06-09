package com.salesianas.dam.replica.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.salesianas.dam.replica.persistence.entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinalProjectRest implements Serializable {


    @JsonProperty("student")
    private StudentEntity student;

    @JsonProperty("title")
    private String title;

    @JsonProperty("expositionDate")
    private LocalTime expositionDate;
}
