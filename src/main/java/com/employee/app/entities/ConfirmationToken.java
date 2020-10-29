package com.employee.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "confirm_table")
public class ConfirmationToken {
    @Id
    @Column(name = "confirm_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int confirm_id;

    @Column(name = "confirma_token")
    private String confirmToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @OneToOne(targetEntity = Users.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Users users;

    public ConfirmationToken(Users user){
        this.users=user;
        createDate= new Date();
        confirmToken= UUID.randomUUID().toString();
    }
}
