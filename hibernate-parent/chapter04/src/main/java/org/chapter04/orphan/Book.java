
package org.chapter04.orphan;

import javax.persistence.*;

@Entity
public class Book {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   @Column
   private String      title;
   @ManyToOne
   private Library     library;

   public Book() {
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public Library getLibrary() {
      return library;
   }

   public void setLibrary(Library library) {
      this.library = library;
   }

}
