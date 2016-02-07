
package org.chapter03.hibernate.entity;

import javax.persistence.*;

@Entity
public class Ranking {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int    id;

   @ManyToOne
   private Person subject;

   @ManyToOne
   private Person observer;

   @ManyToOne
   private Skill  skill;

   @Column
   Integer        ranking;

   public Ranking() {
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public Person getSubject() {
      return subject;
   }

   public void setSubject(Person subject) {
      this.subject = subject;
   }

   public Person getObserver() {
      return observer;
   }

   public void setObserver(Person observer) {
      this.observer = observer;
   }

   public Skill getSkill() {
      return skill;
   }

   public void setSkill(Skill skill) {
      this.skill = skill;
   }

   public Integer getRanking() {
      return ranking;
   }

   public void setRanking(Integer ranking) {
      this.ranking = ranking;
   }

   @Override
   public String toString() {
      return "Ranking{" + "id=" + id + ", subject=" + subject + ", observer=" + observer + ", skill=" + skill + ", ranking=" + ranking + '}';
   }
}