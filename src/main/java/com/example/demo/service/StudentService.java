package com.example.demo.service;

import com.example.demo.DTO.AddStudentRequestDto;
import com.example.demo.DTO.StudentDTO;

import java.util.List;
import java.util.Map;

public interface StudentService{

    List<StudentDTO> getAllStudent();

    StudentDTO getStudentById(long id);

    StudentDTO createNewStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudentById(long id);

    StudentDTO updateStudent(long id, AddStudentRequestDto addStudentRequestDto);

    StudentDTO updatePartialStudent(long id, Map<String, Object> updates);
}
