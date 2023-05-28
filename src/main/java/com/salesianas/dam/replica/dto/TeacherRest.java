package com.salesianas.dam.replica.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.salesianas.dam.replica.persistence.entity.StudentEntity;
import com.salesianas.dam.replica.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherRest implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String username;
    @JsonProperty("name")
    private String name;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("center")
    private String center;

    @JsonProperty("students")
    private List<StudentEntity> students;

    @JsonProperty("login_user")
    private UserEntity login_user;
}