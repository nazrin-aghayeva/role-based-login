package com.employee.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "roles_table")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int role_id;

    public Roles(String role_name) {
        this.role= role_name;
    }

    @Column(name="roles")
    private String role;

    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    public Set<Users> users;
}
