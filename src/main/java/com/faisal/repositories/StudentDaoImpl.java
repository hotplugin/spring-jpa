/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faisal.repositories;

import com.faisal.entities.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hotplugin
 */
@Repository
@Transactional
public class StudentDaoImpl implements StudentDao{

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public Student findStudent(String username) {
	return (Student) em.createQuery("select u from Student u where u.firstName = :username")
		.setParameter("username", username).getSingleResult();
    }
    
    @Override
    public void addStudent(Student st){
        em.persist(st);
        
    }
    
    @Override
    public List<Student>findAllStudent(){
    
        return em.createNamedQuery("Student.findAll").getResultList();
    }
}
