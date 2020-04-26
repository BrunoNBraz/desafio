package com.conquer.desafio.service;

import com.conquer.desafio.model.Student;
import com.conquer.desafio.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Student findById(Integer id){
        return studentRepository.findById(id).get();
    }

    public Student save(Student student){
        return studentRepository.save(student);
    }

    public void delete(Integer id){
        studentRepository.deleteById(id);
    }

    public List<Student> findAllByIdNotIn(List<Student> students){
        List<Integer> ids = new ArrayList<>();
        ids.add(0);
        for( Student student : students){
            ids.add(student.getId());
        }
        return studentRepository.findAllByIdNotIn(ids);
    }

}
