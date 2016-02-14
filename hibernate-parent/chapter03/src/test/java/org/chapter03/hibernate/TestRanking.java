
package org.chapter03.hibernate;

import java.util.List;

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
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Hibernate: alter table Ranking drop foreign key FK_j5b0b4anqw41odw0x6pxrbtr2
 * Hibernate: alter table Ranking drop foreign key FK_8fc03wy4pr9ntrnt8ncfthr6w
 * Hibernate: alter table Ranking drop foreign key FK_ffv5e9op8w3qx3jeqaqbdcsno
 * Hibernate: drop table if exists Person
 * Hibernate: drop table if exists Ranking
 * Hibernate: drop table if exists Skill
 * Hibernate: create table Person (id integer not null auto_increment, name varchar(255), primary key (id))
 * Hibernate: create table Ranking (id integer not null auto_increment, ranking integer, observer_id integer, skill_id integer, subject_id integer, primary key (id))
 * Hibernate: create table Skill (id integer not null auto_increment, name varchar(255), primary key (id))
 * Hibernate: alter table Ranking add index FK_j5b0b4anqw41odw0x6pxrbtr2 (observer_id), add constraint FK_j5b0b4anqw41odw0x6pxrbtr2 foreign key (observer_id) references Person (id)
 * Hibernate: alter table Ranking add index FK_8fc03wy4pr9ntrnt8ncfthr6w (skill_id), add constraint FK_8fc03wy4pr9ntrnt8ncfthr6w foreign key (skill_id) references Skill (id)
 * Hibernate: alter table Ranking add index FK_ffv5e9op8w3qx3jeqaqbdcsno (subject_id), add constraint FK_ffv5e9op8w3qx3jeqaqbdcsno foreign key (subject_id) references Person (id)
 * Hibernate: select person0_.id as id1_0_, person0_.name as name2_0_ from Person person0_ where person0_.name=?
 * Hibernate: insert into Person (name) values (?)
 * Hibernate: select person0_.id as id1_0_, person0_.name as name2_0_ from Person person0_ where person0_.name=?
 * Hibernate: insert into Person (name) values (?)
 * Hibernate: select skill0_.id as id1_2_, skill0_.name as name2_2_ from Skill skill0_ where skill0_.name=?
 * Hibernate: insert into Skill (name) values (?)
 * Hibernate: insert into Ranking (observer_id, ranking, skill_id, subject_id) values (?, ?, ?, ?)
 * Hibernate: select person0_.id as id1_0_, person0_.name as name2_0_ from Person person0_ where person0_.name=?
 * Hibernate: select person0_.id as id1_0_, person0_.name as name2_0_ from Person person0_ where person0_.name=?
 * Hibernate: insert into Person (name) values (?)
 * Hibernate: select skill0_.id as id1_2_, skill0_.name as name2_2_ from Skill skill0_ where skill0_.name=?
 * Hibernate: insert into Ranking (observer_id, ranking, skill_id, subject_id) values (?, ?, ?, ?)
 * Hibernate: select person0_.id as id1_0_, person0_.name as name2_0_ from Person person0_ where person0_.name=?
 * Hibernate: select person0_.id as id1_0_, person0_.name as name2_0_ from Person person0_ where person0_.name=?
 * Hibernate: insert into Person (name) values (?)
 * Hibernate: select skill0_.id as id1_2_, skill0_.name as name2_2_ from Skill skill0_ where skill0_.name=?
 * Hibernate: insert into Ranking (observer_id, ranking, skill_id, subject_id) values (?, ?, ?, ?)
 * Hibernate: select ranking0_.id as id1_1_, ranking0_.observer_id as observer3_1_, ranking0_.ranking as ranking2_1_, ranking0_.skill_id as skill_id4_1_, ranking0_.subject_id as subject_5_1_ from
 * Ranking ranking0_ cross join Person person1_ cross join Skill skill2_ where ranking0_.subject_id=person1_.id and ranking0_.skill_id=skill2_.id and person1_.name=? and skill2_.name=?
 * Hibernate: select person0_.id as id1_0_0_, person0_.name as name2_0_0_ from Person person0_ where person0_.id=?
 * Hibernate: select skill0_.id as id1_2_0_, skill0_.name as name2_2_0_ from Skill skill0_ where skill0_.id=?
 * Hibernate: select person0_.id as id1_0_0_, person0_.name as name2_0_0_ from Person person0_ where person0_.id=?
 * Hibernate: select person0_.id as id1_0_0_, person0_.name as name2_0_0_ from Person person0_ where person0_.id=?
 * Hibernate: select person0_.id as id1_0_0_, person0_.name as name2_0_0_ from Person person0_ where person0_.id=?
 * Ranking{id=1, subject=Person [id=1, name=J. C. Smell], observer=Person [id=2, name=Gene Showrama], skill=Skill{id=1, name='Java'}, ranking=6}
 * Ranking{id=2, subject=Person [id=1, name=J. C. Smell], observer=Person [id=3, name=Scottball Most], skill=Skill{id=1, name='Java'}, ranking=7}
 * Ranking{id=3, subject=Person [id=1, name=J. C. Smell], observer=Person [id=4, name=Drew Lombardo], skill=Skill{id=1, name='Java'}, ranking=8}
 * PASSED: testRankings
 */
public class TestRanking {

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
   public void testRankings() {
      populateRankingData();
      Session session = factory.openSession();
      Transaction tx = session.beginTransaction();
      Query query = session.createQuery("from Ranking r " + "where r.subject.name=:name " + "and r.skill.name=:skill");
      query.setString("name", "J. C. Smell");
      query.setString("skill", "Java");
      int sum = 0;
      int count = 0;
      for (Ranking r : (List<Ranking>) query.list()) {
         count++;
         sum += r.getRanking();
         System.out.println(r);
      }
      int average = sum / count;
      tx.commit();
      session.close();
      Assert.assertEquals(average, 7);
   }

   private void populateRankingData() {
      Session session = factory.openSession();
      Transaction tx = session.beginTransaction();
      createData(session, "J. C. Smell", "Gene Showrama", "Java", 6);
      createData(session, "J. C. Smell", "Scottball Most", "Java", 7);
      createData(session, "J. C. Smell", "Drew Lombardo", "Java", 8);
      createData(session, "J. C. Smell", "Drew Lombardo2", "Java", 8);
      tx.commit();
      session.close();
   }

   private void createData(Session session, String subjectName, String observerName, String skillName, int rank) {
      Person subject = savePerson(session, subjectName);
      Person observer = savePerson(session, observerName);
      Skill skill = saveSkill(session, skillName);

      Ranking ranking = new Ranking();
      ranking.setSubject(subject);
      ranking.setObserver(observer);
      ranking.setSkill(skill);
      ranking.setRanking(rank);
      session.save(ranking);
   }

   private Skill findSkill(Session session, String name) {
      Query query = session.createQuery("from Skill s where s.name=:name");
      query.setParameter("name", name);
      Skill skill = (Skill) query.uniqueResult();
      return skill;
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
