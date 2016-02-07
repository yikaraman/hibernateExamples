
package org.chapter03.hibernate;

import static org.testng.Assert.assertEquals;

import org.chapter03.application.HibernateRankingService;
import org.chapter03.application.RankingService;
import org.testng.annotations.Test;

public class RemoveRankingTest {
   RankingService service = new HibernateRankingService();

   @Test
   public void removeRanking() {
      service.addRanking("R1", "R2", "RS1", 8);
      assertEquals(service.getRankingFor("R1", "RS1"), 8);
      service.removeRanking("R1", "R2", "RS1");
      assertEquals(service.getRankingFor("R1", "RS1"), 0);
   }

   @Test
   public void removeNonexistentRanking() {
      service.removeRanking("R3", "R4", "RS2");
   }
}
