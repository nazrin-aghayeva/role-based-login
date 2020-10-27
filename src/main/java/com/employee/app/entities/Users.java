package com.employee.app.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Data
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

    @ManyToMany(cascade=CascadeType.ALL, fetch =FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "u_id", referencedColumnName ="user_id" )},
            inverseJoinColumns = {@JoinColumn(name = "r_id", referencedColumnName = "role_id")}
    )
    private Set<Roles> roles;
}

