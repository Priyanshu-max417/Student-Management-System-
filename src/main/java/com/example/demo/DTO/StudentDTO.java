package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor // use to create required Constructor
@NoArgsConstructor
public class StudentDTO {
    private  Long id ;
    private String name;
    private String email;

}

