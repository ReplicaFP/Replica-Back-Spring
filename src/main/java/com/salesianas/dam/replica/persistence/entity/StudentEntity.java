package com.salesianas.dam.replica.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@Data
@EqualsAndHashCode(callSuper = false)
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "last_name")
    private String lastName;


    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity login_user;


    @ManyToOne
    private TeacherEntity teacher;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<InternshipEntity> internships;

    @OneToOne
    //@JoinColumn(name = "finalProject" )
    private FinalProjectEntity finalProject;
}
