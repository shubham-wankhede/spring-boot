package com.ws;

import com.entity.Student;
import com.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/student")
@Validated
public class StudentRestController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("/fetch-all-student")
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok((studentService.getAllStudents()));
    }

    @GetMapping("/fetch-student/{id}")
    public ResponseEntity<Student> getStudentById(@Size(max = 20) @PathVariable("id") String id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping("/save-student")
    public ResponseEntity<HttpStatus> saveStudent(@Pattern (regexp = "^(?i:registered|active|inactive)$")
                                                      @RequestParam("status") String status,
                                     @Valid @RequestBody Student student){
        student.setStatus(status);
        String id = studentService.saveStudent(student);

        if(id==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
