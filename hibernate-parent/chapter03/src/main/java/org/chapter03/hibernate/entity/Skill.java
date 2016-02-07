
package org.chapter03.hibernate.entity;

import javax.persistence.*;

@Entity
public class Skill {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   
   @Column
   String      name;

   public Skill() {
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return "Skill{" + "id=" + id + ", name='" + name + '\'' + '}';
   }
}
