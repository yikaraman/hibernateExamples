
package org.chapter04.identity;

import org.chapter00_util.hibernate.SessionUtil;
import org.chapter04.identity.GeneratedAutoIdentity;
import org.chapter04.identity.GeneratedIdentityIdentity;
import org.chapter04.identity.GeneratedSequenceIdentity;
import org.chapter04.identity.GeneratedTableIdentity;
import org.chapter04.identity.NongeneratedIdentity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.id.IdentifierGenerationException;
import org.testng.annotations.Test;

public class IdentityTest {
//   @Test
//   public void testAutoIdentity() {
//      Session session = SessionUtil.getSession();
//      Transaction tx = session.beginTransaction();
//
//      GeneratedAutoIdentity obj = new GeneratedAutoIdentity();
//
//      session.persist(obj);
//
//      tx.commit();
//      session.close();
//
//      System.out.println(obj.getId());
//   }
//
//   @Test(expectedExceptions = IdentifierGenerationException.class)
//   public void testNongeneratedIdentityFailure() {
//      Session session = SessionUtil.getSession();
//      Transaction tx = session.beginTransaction();
//
//      NongeneratedIdentity obj = new NongeneratedIdentity();
//
//      session.persist(obj);
//
//      tx.commit();
//      session.close();
//
//      System.out.println(obj.getId());
//   }
//
//   @Test
//   public void testNongeneratedIdentity() {
//      Session session = SessionUtil.getSession();
//      Transaction tx = session.beginTransaction();
//
//      NongeneratedIdentity obj = new NongeneratedIdentity();
//      obj.setId(1l);
//      session.persist(obj);
//
//      tx.commit();
//      session.close();
//
//      System.out.println(obj.getId());
//   }
//
//   @Test
//   public void testSequenceIdentity() {
//      Session session = SessionUtil.getSession();
//      Transaction tx = session.beginTransaction();
//
//      GeneratedSequenceIdentity obj = new GeneratedSequenceIdentity();
//      session.persist(obj);
//
//      tx.commit();
//      session.close();
//
//      System.out.println(obj.getId());
//   }
//
//   @Test
//   public void testTableIdentity() {
//      Session session = SessionUtil.getSession();
//      Transaction tx = session.beginTransaction();
//
//      GeneratedTableIdentity obj = new GeneratedTableIdentity();
//      session.persist(obj);
//
//      tx.commit();
//      session.close();
//
//      System.out.println(obj.getId());
//   }
//
//   @Test
//   public void testIdentityIdentity() {
//      Session session = SessionUtil.getSession();
//      Transaction tx = session.beginTransaction();
//
//      GeneratedIdentityIdentity obj = new GeneratedIdentityIdentity();
//      session.persist(obj);
//
//      tx.commit();
//      session.close();
//
//      System.out.println(obj.getId());
//   }
}
