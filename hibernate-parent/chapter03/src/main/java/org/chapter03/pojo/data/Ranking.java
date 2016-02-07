
package org.chapter03.pojo.data;

public class Ranking {
   private Person  subject;
   private Person  observer;
   private Skill   skill;
   private Integer ranking;

   public Ranking() {
   }

   public Ranking(Person subject, Person observer, Skill skill, Integer ranking) {
      super();
      this.subject = subject;
      this.observer = observer;
      this.skill = skill;
      this.ranking = ranking;
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
      return "Ranking{" + "subject=" + subject + ", observer=" + observer + ", skill=" + skill + ", ranking=" + ranking + '}';
   }
}