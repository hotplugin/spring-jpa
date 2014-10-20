/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faisal.controllers;

import com.faisal.entities.Student;
import com.faisal.repositories.StudentDao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hotplugin
 */
@Controller
public class HelloWorld {

    @Autowired
    StudentDao studentDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getdata() {
        System.out.println("-------------------ccccccccccccc----------------");
        List<String> list = getList();
        ModelAndView model = new ModelAndView("hello");
        model.addObject("msg", "ni ssss");
        model.addObject("lists", list);
        model.addObject("student", new Student());
        model.addObject("list",getStudentsList());
//        Student s = studentDao.findStudent("faisal");
//        System.out.println(s.getLastName());
//        model.addObject("name", s.getFirstName()+ " "+s.getLastName());
        return model;

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView adduser(@ModelAttribute("student") Student st) {
        System.out.println("called d" + st.getFirstName());
        studentDao.addStudent(st);
        return new ModelAndView("hello","list",getStudentsList());
//        return "hello";

    }

    List<Student>getStudentsList(){
    return studentDao.findAllStudent();
    }
    private List<String> getList() {

        List<String> list = new ArrayList<String>();
        list.add("List A");
        list.add("List B");
        list.add("List C");
        list.add("List D");
        list.add("List 1");
        list.add("List 2");
        list.add("List 3");

        return list;

    }

}
