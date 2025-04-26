package com.jpa;

import com.jpa.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

/// Найти студента по Id
public class JPASelect {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введи id студента");
        Long id = sc.nextLong();
        Student student = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager manager = factory.createEntityManager();

        try {
            student = manager.find(Student.class, id);      // Найти студента с данным Id

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            manager.close();
            factory.close();
        }
        System.out.println(student);
    }
}
