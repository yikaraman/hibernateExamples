
package org.chapter00_util.JPA;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.chapter00_util.model.ThingEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

public class JPASessionUtilTest {
   @Test
   public void getEntityManager() {
      EntityManager em = JPASessionUtil.getEntityManager("jpa-example");
      em.close();
   }

   @Test(expectedExceptions = {javax.persistence.PersistenceException.class})
   public void nonexistentEntityManagerName() {
      JPASessionUtil.getEntityManager("nonexistent");
      fail("We shouldn't be able to acquire an EntityManager here");
   }

   @Test
   public void getSession() {
      Session session = JPASessionUtil.getSession("jpa-example");
      session.close();
   }

   @Test(expectedExceptions = {javax.persistence.PersistenceException.class})
   public void nonexistentSessionName() {
      JPASessionUtil.getSession("nonexistent");
      fail("We shouldn't be able to acquire a Session here");
   }

   @Test
   public void testEntityManager() {
      EntityManager em = JPASessionUtil.getEntityManager("jpa-example");
      em.getTransaction().begin();
      ThingEntity t = new ThingEntity();
      t.setName("Ahmet");
      em.persist(t);// Commited to DB.
      em.getTransaction().commit();
      em.close();

      em = JPASessionUtil.getEntityManager("jpa-example");
      em.getTransaction().begin();
      Query q = em.createQuery("from ThingEntity t where t.name=:name");
      q.setParameter("name", "Ahmet");
      ThingEntity result = (ThingEntity) q.getSingleResult();
      assertNotNull(result);
      em.remove(result);
      em.getTransaction().commit();
      em.close();
   }

   @Test
   public void testSession() {
      Session session = JPASessionUtil.getSession("jpa-example");
      Transaction tx = session.beginTransaction();
      ThingEntity t = new ThingEntity();
      t.setName("Thing 2");
      session.persist(t);
      tx.commit();
      session.close();

      session = JPASessionUtil.getSession("jpa-example");
      tx = session.beginTransaction();
      org.hibernate.Query q = session.createQuery("from ThingEntity t where t.name=:name");
      q.setParameter("name", "Thing 2");
      ThingEntity result = (ThingEntity) q.uniqueResult();
      assertNotNull(result);
      session.delete(result);
      tx.commit();
      session.close();
   }
}
