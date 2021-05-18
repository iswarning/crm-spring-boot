package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "juridical")
public class Juridical implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "contract_info")
    private String contractInfo;

    @Column(name = "status")
    private String contractStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "notarized_date")
    private LocalDate notarizedDate;

    @NotEmpty(message = "registration_procedures is required")
    @Column(name = "registration_procedures")
    private String registrationProcedures;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "delivery_book_date")
    private LocalDate deliveryBookDate;

    @Column(name = "liquidation")
    private boolean liquidation;

    @Column(name = "bill_profile")
    private String billProfile;

    @NotNull(message = "book_holder is required")
    @Column(name = "book_holder")
    private String bookHolder;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "delivery_land_date")
    private LocalDate deliveryLandDate;

    @Column(name = "commitment")
    private String commitment;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    @JsonBackReference
    private Contract contract;

    public boolean isLiquidation(){
        return liquidation;
    }
}
