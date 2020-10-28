package com.employee.app.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_table")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int user_id;

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "email")
    public String email;

    @Column(name = "password")
    private String password;

    @Column(name = "confirm_password")
    private String confirm_password;

    @Column(name = "active")
    private int active;

    @Column(name = "created_time")
    private LocalDateTime created_time;


    @JsonManagedReference
    @ManyToMany(cascade=CascadeType.MERGE, fetch =FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "u_id", referencedColumnName ="user_id" )},
            inverseJoinColumns = {@JoinColumn(name = "r_id", referencedColumnName = "role_id")}
    )
    private Set<Roles> roles;
}

