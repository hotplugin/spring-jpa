/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faisal.repositories;

import com.faisal.entities.Ask;
import com.faisal.entities.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hotplugin
 */
@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

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
    public void addStudent(Student st) {
        em.persist(st);

    }

    @Override
    public List<Student> findAllStudent() {

        return em.createNamedQuery("Student.findAll").getResultList();
    }

    public boolean addRow(Ask a) {
        try {

            em.persist(a);
//             System.out.println("OK");
        } catch (Exception e) {
            System.out.println("addrow error: " + e.getMessage());
            return false;
        }
        return true;
    }

    public List<Ask> download(String from,String to) {
        try {
            Query q = em.createQuery("Select a from Ask a where a.timeanddatestring between ?1 and ?2");
            q.setParameter(1, from);
            q.setParameter(2,to);
            List<Ask> results = q.getResultList();
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
