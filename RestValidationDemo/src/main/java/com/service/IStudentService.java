package com.service;

import com.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IStudentService{
    public Student getStudentById(String id);
    public List<Student> getAllStudents();
    public String saveStudent(Student student);
}
