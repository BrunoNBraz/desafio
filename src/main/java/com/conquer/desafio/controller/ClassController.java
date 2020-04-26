package com.conquer.desafio.controller;

import com.conquer.desafio.model.Class;
import com.conquer.desafio.service.ClassService;
import com.conquer.desafio.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/turma")
public class ClassController {
    @Autowired
    private ClassService classService;

    @Autowired
    private StudentService studentService;

    @GetMapping({"/", "/lista", ""})
    public String listAll(Model model){
        model.addAttribute("classes", classService.findAll());
        return "class/ClassList";
    }

    @GetMapping("/cadastrar")
    public String addClass(Model model){
        model.addAttribute("classModel", new Class());
        model.addAttribute("students", studentService.findAll());
        return "class/ClassForm";
    }

    @GetMapping("/editar/{id}")
    public String editClass(@PathVariable("id") Integer id, Model model){
        Class classModel = classService.findById(id);
        model.addAttribute("classModel", classModel);
        model.addAttribute("students", studentService.findAllByIdNotIn(classModel.getStudents()));
        return "class/ClassForm";
    }

    @PostMapping(value="/addstudent")
    public String addStudent(@RequestBody Class classModel, @RequestParam(name = "idStudent") Integer idStudent, Model model) {
        if(classModel == null){
            classModel = new Class();
        }
        classModel = classService.addStudent(classModel, studentService.findById(idStudent));
        model.addAttribute("classModel", classModel);
        model.addAttribute("students", studentService.findAllByIdNotIn(classModel.getStudents()));
        return "class/ClassForm";
    }

    @PostMapping(value="/removestudent")
    public String removeStudent(@RequestBody Class classModel, @RequestParam(name = "rowRemove") Integer rowRemove, Model model) {
        if(classModel == null){
            classModel = new Class();
        }
        classModel = classService.removeStudent(classModel, rowRemove);
        model.addAttribute("classModel", classModel);
        model.addAttribute("students", studentService.findAllByIdNotIn(classModel.getStudents()));
        return "class/ClassForm";
    }

    @PostMapping("/save")
    public String saveClass(@Valid @ModelAttribute("classModel") Class classModel, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("classModel", classModel);
            return "class/ClassForm";
        }

        if(classModel.getId() != null){
            model.addAttribute("updateSuccess", true);
        }
        else{
            model.addAttribute("insertSuccess", true);
        }

        classService.save(classModel);
        return listAll(model);
    }

    @GetMapping("/excluir/{id}")
    public String deleteClass(@PathVariable("id") Integer id, Model model){
        classService.delete(id);

        model.addAttribute("deleteSuccess", true);
        return listAll(model);
    }

}
