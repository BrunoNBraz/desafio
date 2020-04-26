package com.conquer.desafio.service;

import com.conquer.desafio.model.Class;
import com.conquer.desafio.model.Student;
import com.conquer.desafio.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private StudentService studentService;

    public ClassService(ClassRepository classRepository){
        this.classRepository = classRepository;
    }

    public List<Class> findAll(){
        return classRepository.findAll();
    }

    public Class findById(Integer id){
        return classRepository.findById(id).get();
    }

    public Class save(Class classModel){
        return classRepository.save(classModel);
    }

    public void delete(Integer id){
        classRepository.deleteById(id);
    }

    public Class addStudent(Class classModel, Student student){
        classModel.setStudents(reloadStudents(classModel.getStudents()));

        classModel.getStudents().add(student);
        return classModel;
    }

    public Class removeStudent(Class classModel, Integer rowRemove){
        classModel.getStudents().remove(classModel.getStudents().get(rowRemove));
        classModel.setStudents(reloadStudents(classModel.getStudents()));

        return classModel;
    }

    public List<Student> reloadStudents(List<Student> students){
        if(!students.isEmpty()) {
            for (int i = 0; i < students.size(); i++) {
                students.set(i, studentService.findById(students.get(i).getId()));
            }
        }

        return students;
    }
}
