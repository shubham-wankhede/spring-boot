package com.service.impl;

import com.entity.Student;
import com.repo.StudentRepository;
import com.service.IStudentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private StudentRepository studentRepo;

    @Override
    public List<Student> getAllStudents() {
        List<Student> list = studentRepo.findAll();
        return list;
    }

    @Override
    public Student getStudentById(Integer id) {
        if(!studentRepo.existsById(id)){
            return null;
        }
        return studentRepo.getOne(id);
    }

    @Override
    public List<Student> findStudentsByIds(List<Integer> ids) {
        List<Student> studentList = null;
        if(!ids.isEmpty()) {
            studentList = studentRepo.findByIdIn(ids);
        }else {
            studentList = studentRepo.findAll();
        }
        return studentList;
    }

    @Override
    public Integer saveStudent(Student student) {
        studentRepo.save(student);
        return student.getId();
    }
}
