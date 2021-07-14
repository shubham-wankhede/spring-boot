package com.service;

import com.entity.Student;

import java.util.List;

public interface IStudentService {
    public List<Student> getAllStudents();
    public Student getStudentById(Integer id);
    public List<Student> findStudentsByIds(List<Integer> ids);
    public Integer saveStudent(Student student);
}
