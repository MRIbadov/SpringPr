package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class StudentService {
    private final StudentRepository studentRepository;
@Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

        Optional<Student> studentbyemail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentbyemail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long id) {
            boolean exsist = studentRepository.existsById(id);
            if(exsist){
                throw new IllegalStateException("Student with id " + id + " does not exisit ");

            }

            studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException("Student with id " + studentId + " does not exisit "));

        if(name != null && name.length() > 0  && !Objects.equals(student.getName(), name) ){
            student.setName(name);
        }

        if(email != null && email.length() > 0  && !Objects.equals(student.getEmail(), email) ){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email was taken");
            }
            student.setEmail(email);
        }



    }
}

