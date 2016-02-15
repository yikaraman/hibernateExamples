
package org.chapter04.model;

import javax.persistence.*;

@Entity
public class MyObjSimple {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column
   private String  keyStr;

   @Column
   Integer         valueObj;

   public MyObjSimple() {
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getKeyStr() {
      return keyStr;
   }

   public void setKeyStr(String keyStr) {
      this.keyStr = keyStr;
   }

   public Integer getValueObj() {
      return valueObj;
   }

   public void setValueObj(Integer valueObj) {
      this.valueObj = valueObj;
   }

   @Override
   public String toString() {
      return "myObjSimple [id=" + id + ", keyStr=" + keyStr + ", valueObj=" + valueObj + "]";
   }

   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (!(o instanceof MyObjSimple))
         return false;

      MyObjSimple that = (MyObjSimple) o;

      // we prefer the method versions of accessors, because of Hibernate's proxies.
      if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null)
         return false;
      if (getKeyStr() != null ? !getKeyStr().equals(that.getKeyStr()) : that.getKeyStr() != null)
         return false;
      if (getValueObj() != null ? !getValueObj().equals(that.getValueObj()) : that.getValueObj() != null)
         return false;

      return true;
   }

   @Override
   public int hashCode() {
      int result = getId() != null ? getId().hashCode() : 0;
      result = 31 * result + (getKeyStr() != null ? getKeyStr().hashCode() : 0);
      result = 31 * result + (getValueObj() != null ? getValueObj().hashCode() : 0);
      return result;
   }

}
