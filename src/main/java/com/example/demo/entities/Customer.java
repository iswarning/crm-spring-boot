package com.example.demo.entities;


import com.example.demo.validator.DateFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Name is required")
    private String name;

    private int cmnd;

    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private String household;

    private int phone;

    @OneToMany(mappedBy="customer",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Contract> contracts = new ArrayList<>();

}
