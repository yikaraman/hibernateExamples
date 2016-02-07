package org.chapter03.hibernate;

import static org.testng.Assert.assertEquals;

import org.chapter03.application.HibernateRankingService;
import org.chapter03.application.RankingService;
import org.testng.annotations.Test;

public class AddRankingTest {
    RankingService service = new HibernateRankingService();

    @Test
    public void addRanking() {
        service.addRanking("J. C. Smell", "Drew Lombardo", "Mule", 8);
        assertEquals(service.getRankingFor("J. C. Smell", "Mule"), 8);
    }
}
