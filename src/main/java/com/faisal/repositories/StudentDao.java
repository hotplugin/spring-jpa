/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faisal.repositories;

import com.faisal.entities.Ask;
import com.faisal.entities.Student;
import java.util.List;

/**
 *
 * @author hotplugin
 */
public interface StudentDao {
    
     public Student findStudent(String username);
     
     public void addStudent(Student st);
     
     public List<Student>findAllStudent();
     
     public boolean addRow(Ask a);
}
