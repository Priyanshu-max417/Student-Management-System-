package com.example.demo.controller;

import com.example.demo.DTO.AddStudentRequestDto;
import com.example.demo.DTO.StudentDTO;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor // Use to Create Required Constructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudent(){
//        return ResponseEntity.status(HttpStatus.OK).body( studentService.getAllStudent()); //First Way to show HTTP status
        return ResponseEntity.ok(studentService.getAllStudent()); //Second Way to show HTTP status (only 200 code)
    }

    @GetMapping("/{id}")
    public ResponseEntity <StudentDTO>getStudentId(@PathVariable long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createNewStudent(@RequestBody @Valid AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentRequestDto));
    }

    @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteAStudent(@PathVariable long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable long id ,@RequestBody AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.ok(studentService.updateStudent(id , addStudentRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDTO> updatePartialStudent(@PathVariable long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(studentService.updatePartialStudent(id, updates));
    }

}
