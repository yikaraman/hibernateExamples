
package org.chapter02.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Hibernate: drop table if exists myhibernatedb.MESSAGE
 * Hibernate: create table myhibernatedb.MESSAGE (MESSAGE_ID integer not null auto_increment, TEXT varchar(10) not null, primary key (MESSAGE_ID))
 * Hibernate: alter table myhibernatedb.MESSAGE add constraint UK_451ygs9r5ujh8h88d6xgabxq8 unique (TEXT)
 * Hibernate: insert into myhibernatedb.MESSAGE (TEXT) values (?)
 * Hibernate: select message0_.MESSAGE_ID as MESSAGE_1_0_, message0_.TEXT as TEXT2_0_ from myhibernatedb.MESSAGE message0_
 * Message{id=1, text='Hello'}
 */
public class PersistenceTest {
   SessionFactory factory;

   @BeforeSuite
   public void setup() {
      Configuration configuration = new Configuration();
      configuration.configure();
      ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
      srBuilder.applySettings(configuration.getProperties());
      ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
      factory = configuration.buildSessionFactory(serviceRegistry);
   }

   @Test
   public void saveMessage() {
      Message message = new Message("Hello");
      Session session = factory.openSession();
      Transaction tx = session.beginTransaction();
      session.persist(message);
      tx.commit();
      session.close();
   }

   @Test(dependsOnMethods = "saveMessage")
   public void readMessage() {
      Session session = factory.openSession();
      @SuppressWarnings("unchecked")
      List<Message> list = (List<Message>) session.createQuery("from Message").list();

      if (list.size() > 1) {
         Assert.fail("Message configuration in error; table should contain only one." + " Set ddl to drop-create.");
      }
      if (list.size() == 0) {
         Assert.fail("Read of initial message failed; check saveMessage() for errors." + " How did this test run?");
      }
      for (Message m : list) {
         System.out.println(m);
      }
      session.close();
   }
}
