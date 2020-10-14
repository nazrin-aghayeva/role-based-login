package com.employee.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "workers_table")
public class Workers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worker_id")
    private int worker_id;

    @Column(name="full_name")
    private String full_name;

    @Column(name = "age")
    private int age;

    @Column(name = "job_status")
    private String job_status;

    @Column(name = "salary")
    private String salary;
}
