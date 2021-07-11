package com.service.impl;

import com.entity.Student;
import com.exception.ResourceAlreadyExistsException;
import com.exception.ResourceNotFoundException;
import com.repo.StudentRepository;
import com.service.IStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository studentRepo;

    private Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Override
    public Student getStudentById(String id) {
        if(!studentRepo.existsById(id)){
            logger.error("Student Not Found with Id:"+id);
            throw new ResourceNotFoundException("No Student Available with Id:"+id);
        }
        return studentRepo.getOne(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public String saveStudent(Student student) {
        student.setId(UUID.randomUUID().toString());
        if(studentRepo.findByNameIgnoreCase(student.getName()).isPresent()){
            throw new ResourceAlreadyExistsException("student with name ("+student.getName()+") already exists.");
        }
        studentRepo.save(student);
        return student.getId();
    }
}
