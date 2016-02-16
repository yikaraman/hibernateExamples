
package org.chapter04.model;

import static org.testng.Assert.assertEquals;

import org.chapter00_util.hibernate.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

public class MergeRefreshTest {
   @Test
   public void testMerge() {
      int id;
      Session session = SessionUtil.getSession();
      Transaction tx = session.beginTransaction();

      MyObjSimple simpleObject = new MyObjSimple();

      simpleObject.setKeyStr("testMerge");
      simpleObject.setValueObj(1000);

      session.save(simpleObject);

      id = simpleObject.getId();

      tx.commit();
      session.close();

      MyObjSimple so = validateSimpleObject(id, 1000);

      so.setValueObj(2000);

      session = SessionUtil.getSession();
      tx = session.beginTransaction();

      session.merge(so); // Update row!!

      tx.commit();
      session.close();

      validateSimpleObject(id, 2000);
   }

   @Test
   public void testRefresh() {
      int id;
      Session session = SessionUtil.getSession();
      Transaction tx = session.beginTransaction();

      MyObjSimple simpleObject = new MyObjSimple();

      simpleObject.setKeyStr("testMerge");
      simpleObject.setValueObj(1000);

      session.save(simpleObject);

      id = simpleObject.getId();

      tx.commit();
      session.close();

      MyObjSimple so = validateSimpleObject(id, 1000);

      so.setValueObj(2000);

      session = SessionUtil.getSession();
      tx = session.beginTransaction();

      session.refresh(so);

      tx.commit();
      session.close();

      validateSimpleObject(id, 1000);
   }

   private MyObjSimple validateSimpleObject(int id, Integer value) {
      Session session;
      Transaction tx;// validate the database values
      session = SessionUtil.getSession();
      tx = session.beginTransaction();

      MyObjSimple so = (MyObjSimple) session.load(MyObjSimple.class, id);

      assertEquals(so.getKeyStr(), "testMerge");
      assertEquals(so.getValueObj(), value);

      tx.commit();
      session.close();

      return so;
   }
}
