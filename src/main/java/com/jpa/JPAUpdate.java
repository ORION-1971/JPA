package com.jpa;

import com.jpa.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Scanner;

/// Изменить оценку студента с данным Id
public class JPAUpdate {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введи id студента кому изменить оценку");
        Long id = sc.nextLong();
        System.out.println("Средняя оценка (5,5)");  // Вводить через запятую
        double avgGrade = sc.nextDouble();
        Student student = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();                            //запуск транзакции

            student = manager.find(Student.class, id);      // найти студента по id
            student.setAvgGrade(avgGrade);

            transaction.commit();                           // совершить транзакцию

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();                     // откат транзакции
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
