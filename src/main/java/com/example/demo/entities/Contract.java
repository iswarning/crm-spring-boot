package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "contracts")
public class Contract implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "contractNo is required")
    @Column(name = "contract_no")
    private String contractNo;

    @NotNull(message = "area_signed can not null")
    @Column(name = "area_signed")
    private double areaSigned;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonBackReference
    private Customer customer;

    @NotEmpty(message = "type is required")
    @Column(name = "type")
    private String type;

    @Column(name = "signed")
    private boolean signed;

    @Column(name = "signed_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate signedDate;

    @NotEmpty(message = "value is required")
    @Column(name = "value")
    private String value;

    @NotEmpty(message = "lot_number is required")
    @Column(name = "lot_number")
    private String lotNumber;

    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "status_created_by")
    private String statusCreatedBy;

    @OneToOne(mappedBy = "contract", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private @NotNull @Valid Payment payment;

    @OneToOne(mappedBy = "contract", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private @NotNull @Valid Juridical juridical;

    public boolean isSigned(){
        return signed;
    }
}
