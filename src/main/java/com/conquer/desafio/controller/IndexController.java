package com.conquer.desafio.controller;

import com.conquer.desafio.service.ClassService;
import com.conquer.desafio.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    private ClassService classService;
    @Autowired
    private StudentService studentService;

    @GetMapping(value = {"/", "/home"})
    public String getIndex(Model model){
        model.addAttribute("students", studentService.findAll().size());
        model.addAttribute("classes", classService.findAll().size());
        return "dashboard/Dashboard";
    }
}
