
package com.yikaraman.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.yikaraman.entity.Employee;

public class MyPersitenceBroker {

   public static void main(String[] args) {

      EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");

      EntityManager entityManager = entityManagerFactory.createEntityManager();

      Employee employee = new Employee("ilkerk", "Karamanli", "my City", "yikaraman@gmail.com");
      entityManager.getTransaction().begin();
      entityManager.persist(employee);
      System.out.println("hey1");

      entityManager.getTransaction().commit();
      System.out.println("hey2");

      entityManager.close();
      System.out.println("hey3");
      
      System.exit(0);
   }

}