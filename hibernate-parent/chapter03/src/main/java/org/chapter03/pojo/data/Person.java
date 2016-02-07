
package org.chapter03.pojo.data;

public class Person {

   private String name;

   public Person() {
   }

   public Person(String name) {
      super();
      this.name = name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   @Override
   public String toString() {
      return "Person [name=" + name + "]";
   }

}
