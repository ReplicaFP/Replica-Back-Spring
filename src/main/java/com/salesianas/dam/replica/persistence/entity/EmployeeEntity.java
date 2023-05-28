package com.salesianas.dam.replica.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
@EqualsAndHashCode(callSuper = false)
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;
    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @JsonIgnore
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<InternshipEntity> internships;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity login_user;
}
