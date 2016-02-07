
package com.yikaraman.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int    id;

   private String name;
   private String surname;
   private String address;
   private String email;

   public Employee() {
      super();
   }

   public Employee(String name, String surname, String address, String email) {
      super();
      this.name = name;
      this.surname = surname;
      this.address = address;
      this.email = email;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSurname() {
      return surname;
   }

   public void setSurname(String surname) {
      this.surname = surname;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @Override
   public String toString() {
      return "Employee [id=" + id + ", name=" + name + ", surname=" + surname + ", address=" + address + ", email=" + email + "]";
   }

}
