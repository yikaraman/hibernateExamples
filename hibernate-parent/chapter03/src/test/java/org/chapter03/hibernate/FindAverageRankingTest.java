package org.chapter03.hibernate;

import static org.testng.Assert.assertEquals;

import org.chapter03.application.HibernateRankingService;
import org.chapter03.application.RankingService;
import org.testng.annotations.Test;

public class FindAverageRankingTest {
    RankingService service = new HibernateRankingService();

    @Test
    public void validateRankingAverage() {
        service.addRanking("A", "B", "C", 4);
        service.addRanking("A", "B", "C", 5);
        service.addRanking("A", "B", "C", 6);
        assertEquals(service.getRankingFor("A", "C"), 5);
        service.addRanking("A", "B", "C", 7);
        service.addRanking("A", "B", "C", 8);
        assertEquals(service.getRankingFor("A", "C"), 6);
    }
}
