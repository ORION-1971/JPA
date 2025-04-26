package com.jpa;

import com.jpa.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Scanner;

/// Удалить студента по Id
public class JPADelete {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введи id студента для удаления");
        Long id = sc.nextLong();
        Student student = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            student = manager.find(Student.class, id);      // Найти студента с данным Id
            manager.remove(student);                        // Удалить

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            manager.close();
            factory.close();
        }
        System.out.println(student);
    }
}
