package com.conquer.desafio.controller;

import com.conquer.desafio.model.Student;
import com.conquer.desafio.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/aluno")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping({"/", "lista", ""})
    public String listAll(Model model){
        model.addAttribute("students", studentService.findAll());
        return "student/StudentList";
    }

    @GetMapping("/cadastrar")
    public String addStudent(Model model){
        model.addAttribute("student", new Student());
        return "student/StudentForm";
    }

    @GetMapping("/editar/{id}")
    public String editStudent(@PathVariable("id") Integer id, Model model){
        model.addAttribute("student", studentService.findById(id));
        return "student/StudentForm";
    }

    @PostMapping("/save")
    public String saveStudent(@Valid Student student, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("student", student);
            return "student/StudentForm";
        }

        if(student.getId() != null){
            model.addAttribute("updateSuccess", true);
        }
        else{
            model.addAttribute("insertSuccess", true);
        }

        studentService.save(student);
        return listAll(model);
    }

    @GetMapping("/excluir/{id}")
    public String deleteStudent(@PathVariable("id") Integer id, Model model){
        studentService.delete(id);

        model.addAttribute("deleteSuccess", true);
        return listAll(model);
    }
}
