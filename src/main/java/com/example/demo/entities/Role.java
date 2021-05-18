package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    private String name;
}
