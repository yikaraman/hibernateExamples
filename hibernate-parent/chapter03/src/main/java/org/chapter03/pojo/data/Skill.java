
package org.chapter03.pojo.data;

public class Skill {
   String name;

   public Skill() {
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return "Skill{" + "name='" + name + '\'' + '}';
   }
}
