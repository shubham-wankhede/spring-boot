package com.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
@Data
public class Student {
    @Id
    private String id;

    @NotEmpty(message = "name cannot be emtpy")
    @NotNull(message = "name cannot be null")
    @Column(length = 30)
    private String name;

    @Min(17)
    @Max(30)
    @Column
    private Integer age;

    @Positive(message = "section cannot be negative")
    @Column
    private Integer section;

    @Email(message = "not a valid format for email")
    @Column(length = 50)
    private String  email;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @NotNull(message = "address cannot be null")
    @Valid
    private Address address;

//    @Pattern(regexp = "^(?i:registered|active|inactive)$"
//            ,message = "status can only be registered or active or inactive")
    @Column(length = 20)
    private String status;
}
