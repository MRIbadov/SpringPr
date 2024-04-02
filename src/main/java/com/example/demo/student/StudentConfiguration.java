package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.CommandLinePropertySource;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
                    Student Aye = new Student(
                            1L,
                            "Aye",
                            "Akifrafiqli@gmail.com",
                            LocalDate.of(2002, Month.AUGUST, 21)
                    );

            Student Leo = new Student(
                    2L,
                    "Leo",
                    "Akifrafiqli@gmail.com",
                    LocalDate.of(2001, Month.APRIL, 1)
            );

            repository.saveAll(List.of(Aye,Leo));

        };
    }
}
