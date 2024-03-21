package com.nishasoni.springboot.controller;

import com.nishasoni.springboot.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    //http://localhost:8080/student
    @GetMapping("student")
    public Student getStudent(){
        Student student = new Student(
                1,
                "Nisha",
                "Soni"
        );
        return student;
    }

    //http://localhost:8080/students
    @GetMapping("students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Nisha" , "Soni"));
        students.add(new Student(2, "Amit", "Soni"));
        return students;
    }

    //http://localhost:8080/students/1/AAA/BBB
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable int id,
                                       @PathVariable("first-name") String firstName,
                                        @PathVariable("last-name") String lastName){
        return new Student(id, firstName,lastName);
    }

    //Request Param
    //http://localhost:8080/students/query?id=1&firstName&lastName
    @GetMapping("students/query")
    public Student studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        return new Student(id, firstName , lastName);
    }

    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    @PutMapping("students/{id}/update")
    public Student updateStudent(@RequestBody Student student, @PathVariable int id){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;

    }

    @DeleteMapping("student/{id}/delete")
    public String deleteStudent(@PathVariable int id){
        System.out.println(id);
        return "Student successfully deleted" ;
    }
}
