package com.example.demo.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.Email;

@Data
public class AddStudentRequestDto {

    @NotBlank(message = "Name is Required")
    @Size(min =3, max = 30, message = "Name should be of length 3 to 30 character")
    private String name;

    @Email
    @NotBlank(message = "Email is Required")
    private String email;

}
