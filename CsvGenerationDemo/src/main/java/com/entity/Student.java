package com.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Student {
    @Id
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "Student_ID")
    private Integer id;

    @Column(length = 30)
    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "Student_Name")
    private String name;

    @Column
    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "Age")
    private Integer age;

    @Column(length = 40)
    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "Study_Field")
    private String studyField;
}
