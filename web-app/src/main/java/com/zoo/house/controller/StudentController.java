package com.zoo.house.controller;

import com.zoo.house.model.po.Student;
import com.zoo.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private UserService  userService;

    @RequestMapping("findAll")
    @ResponseBody
    public List<Student> getAll(){
        List<Student> list = userService.findAll();
        return list;
    }
}
