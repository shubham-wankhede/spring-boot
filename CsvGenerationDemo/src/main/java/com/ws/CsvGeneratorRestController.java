package com.ws;

import com.entity.Student;
import com.service.IStudentService;
import com.utils.CsvUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CsvGeneratorRestController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("/fetch-all-students")
    public ResponseEntity<List<Student>> getAllStudents(){
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/fetch-student/{id}")
    public ResponseEntity<Student> getStudentyById(@PathVariable("id") Integer id){
        Student student = studentService.getStudentById(id);
        HttpStatus status = student==null?HttpStatus.BAD_REQUEST:HttpStatus.OK;
        return new ResponseEntity<>(student, status);
    }

    @PostMapping("/save-student")
    public ResponseEntity<Integer> saveStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentService.saveStudent(student),HttpStatus.OK);
    }

    @GetMapping("/export-csv")
    public ResponseEntity exportStudentsCsv(@RequestParam("ids") List<Integer> ids){
        try {
            List<Student> studentList = studentService.findStudentsByIds(ids);
            StringWriter stringWriter =
                    CsvUtils.createCsv(studentList);
                    //CsvUtils.createSimpleCsv(studentList);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"students.csv\"")
                    .body(stringWriter.toString());

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
