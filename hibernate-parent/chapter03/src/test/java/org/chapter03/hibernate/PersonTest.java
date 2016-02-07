
package org.chapter03.hibernate;

import org.chapter03.hibernate.entity.Person;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Hibernate: drop table if exists Person
 * Hibernate: create table Person (id integer not null auto_increment, name varchar(255), primary key (id))
 * Hibernate: insert into Person (name) values (?)
 * PASSED: testSavePerson
 */
public class PersonTest {
   SessionFactory factory;

   @BeforeClass
   public void setup() {
      Configuration configuration = new Configuration();
      configuration.configure();
      ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
      srBuilder.applySettings(configuration.getProperties());
      ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
      factory = configuration.buildSessionFactory(serviceRegistry);
   }

   @Test
   public void testSavePerson() {
      Session session = factory.openSession();
      Transaction tx = session.beginTransaction();
      Person person = new Person();
      person.setName("J. C. Smell");

      session.save(person);

      tx.commit();
      session.close();
   }
}