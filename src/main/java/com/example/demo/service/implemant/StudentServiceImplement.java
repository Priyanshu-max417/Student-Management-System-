package com.example.demo.service.implemant;

import com.example.demo.DTO.AddStudentRequestDto;
import com.example.demo.DTO.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class StudentServiceImplement implements StudentService {

    private final StudentRepository studentRepository;
    private  final ModelMapper modelMapper;

    @Override
    public List<StudentDTO> getAllStudent() {
        List<Student> students = studentRepository.findAll();
        return students
                .stream()
                .map(student -> modelMapper.map(student , StudentDTO.class))
                .toList();
    }

    @Override
    public StudentDTO getStudentById(long id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Student not Found With Id: "+id));
        return modelMapper.map(student,StudentDTO.class);
    }

    @Override
    public StudentDTO createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newstudent = modelMapper.map(addStudentRequestDto, Student.class );
        Student student = studentRepository.save(newstudent);
        return modelMapper.map(student , StudentDTO.class);

    }

    @Override
    public void deleteStudentById(long id) {
        if(!studentRepository.existsById(id)){
            throw  new IllegalArgumentException("Student does not exists by id: "+id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO updateStudent(long id, AddStudentRequestDto addStudentRequestDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student does not exists by id: "+id));

        modelMapper.map(addStudentRequestDto , student);

        student = studentRepository.save(student);
        return modelMapper.map(student , StudentDTO.class);

    }

    @Override
    public StudentDTO updatePartialStudent(long id, Map<String, Object> updates) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student does not exists by id: "+id));

        updates.forEach((field, value) ->{
            switch (field){
                case "name":
                    student.setName((String) value);
                    break;
                case "email":
                    student.setEmail((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field is not supported");
            }
        });

        Student savedStudent = studentRepository.save(student);
        return modelMapper.map( savedStudent, StudentDTO.class);
    }
}
