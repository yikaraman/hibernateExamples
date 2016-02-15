
package org.chapter04.model;

import org.chapter00_util.hibernate.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PersistingEntitiesTest {

   @Test
   public void testProperSimpleInversionCode() {

      int id = 0;

      Session session = SessionUtil.getSession();
      Transaction tx = session.beginTransaction();

      MyObjSimple objSimple = new MyObjSimple();
      objSimple.setKeyStr("str1");
      objSimple.setValueObj(1500);

      session.save(objSimple);
      assertNotNull(objSimple.getId());
      // we should have an id now, set by Session.save()
      id = objSimple.getId();

      tx.commit();
      session.close();

      session = SessionUtil.getSession();
      tx = session.beginTransaction();

      // we're loading the object by id
      MyObjSimple o2 = (MyObjSimple) session.load(MyObjSimple.class, id);
      assertEquals(o2.getKeyStr(), "str1");
      assertNotNull(o2.getValueObj());
      assertEquals(o2.getValueObj(), (Integer) 1500);

      MyObjSimple o3 = (MyObjSimple) session.load(MyObjSimple.class, id);

      // since o3 and o2 were loaded in the same session, they're not only
      // equivalent - as shown by equals() - but equal, as shown by ==.
      // since obj was NOT loaded in this session, it's equivalent but
      // not ==.
      assertEquals(o2, o3);
      assertEquals(objSimple, o2);

      assertTrue(o2 == o3);
      assertFalse(o2 == objSimple); // Because of different Session!!

      tx.commit();
      session.close();
   }

   @Test
   public void testSavingEntitiesTwice() {
      int id;
      Session session = SessionUtil.getSession();
      Transaction tx = session.beginTransaction();

      MyObjSimple obj = new MyObjSimple();

      obj.setKeyStr("osas");
      obj.setValueObj(10);

      session.save(obj);
      assertNotNull(obj.getId());

      id = obj.getId();

      tx.commit();
      session.close();

      session = SessionUtil.getSession();
      tx = session.beginTransaction();

      obj.setValueObj(12);

      session.save(obj); // if you want to update row .. use saveORUpdate!! // Adds as a new row

      tx.commit();
      session.close();

      // note that save() creates a new row in the database!
      // this is wrong behavior. Don't do this!
      assertNotEquals(id, obj.getId());
   }

   @Test
   public void testSaveOrUpdateEntity() {
      int id;
      Session session = SessionUtil.getSession();
      Transaction tx = session.beginTransaction();

      MyObjSimple obj = new MyObjSimple();

      obj.setKeyStr("osas2");
      obj.setValueObj(14);

      session.save(obj);
      assertNotNull(obj.getId());

      id = obj.getId();

      tx.commit();
      session.close();

      session = SessionUtil.getSession();
      tx = session.beginTransaction();

      obj.setValueObj(12);

      session.saveOrUpdate(obj);

      tx.commit();
      session.close();

      // saveOrUpdate() will update a row in the database
      // if one matches. This is what one usually expects.
      assertEquals((Integer) id, obj.getId());
   }

}
