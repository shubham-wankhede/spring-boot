package com.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    @NotEmpty
    @NotNull
    private String house;

    @Column
    @NotEmpty
    @NotNull
    private String city;

    @Column
    @DecimalMin("400000")
    private Integer pinCode;
}
