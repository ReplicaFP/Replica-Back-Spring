package com.salesianas.dam.replica.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.salesianas.dam.replica.persistence.entity.FinalProjectEntity;
import com.salesianas.dam.replica.persistence.entity.InternshipEntity;
import com.salesianas.dam.replica.persistence.entity.TeacherEntity;
import com.salesianas.dam.replica.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRest implements Serializable {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    private String username;

    @JsonProperty("name")
    private String name;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("login_user")
    private UserEntity login_user;

    @JsonProperty("teacher")
    private TeacherEntity teacher;

    @JsonProperty("internships")
    private List<InternshipEntity> internships;

    @JsonProperty("finalProject")
    private FinalProjectEntity finalProject;
}
