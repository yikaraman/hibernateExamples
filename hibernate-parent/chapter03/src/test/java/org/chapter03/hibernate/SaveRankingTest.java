
package org.chapter03.hibernate;

import org.chapter03.hibernate.entity.Person;
import org.chapter03.hibernate.entity.Ranking;
import org.chapter03.hibernate.entity.Skill;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SaveRankingTest {

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
   public void testSaveRanking() {
      Session session = factory.openSession();
      Transaction tx = session.beginTransaction();
      Person subject = savePerson(session, "J. C. Smell");
      Person observer = savePerson(session, "Drew Lombardo");
      Skill skill = saveSkill(session, "Java");
      Ranking ranking = new Ranking();
      ranking.setSubject(subject);
      ranking.setObserver(observer);
      ranking.setSkill(skill);
      ranking.setRanking(8);
      session.save(ranking);
      tx.commit();
      session.close();
   }

   private Skill saveSkill(Session session, String skillName) {
      Skill skill = findSkill(session, skillName);
      if (skill == null) {
         skill = new Skill();
         skill.setName(skillName);
         session.save(skill);
      }
      return skill;
   }

   private Skill findSkill(Session session, String name) {
      Query query = session.createQuery("from Skill s where s.name=:name");
      query.setParameter("name", name);
      Skill skill = (Skill) query.uniqueResult();
      return skill;
   }

   private Person savePerson(Session session, String name) {
      Person person = findPerson(session, name);
      if (person == null) {
         person = new Person();
         person.setName(name);
         session.save(person);
      }
      return person;
   }

   private Person findPerson(Session session, String name) {
      Query query = session.createQuery("from Person p where p.name=:name");
      query.setParameter("name", name);
      Person person = (Person) query.uniqueResult();
      return person;
   }
}
