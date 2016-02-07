package org.chapter03.hibernate;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.chapter03.application.HibernateRankingService;
import org.chapter03.application.RankingService;
import org.chapter03.hibernate.entity.Person;
import org.testng.annotations.Test;

public class FindBestRankingTest {
    RankingService service = new HibernateRankingService();

    @Test
    public void findBestForNonexistentSkill() {
        Person p = service.findBestPersonFor("no skill");
        assertNull(p);
    }

    @Test
    public void findBestForSkill() {
        service.addRanking("S1", "O1", "Sk1", 6);
        service.addRanking("S1", "O2", "Sk1", 8);
        service.addRanking("S2", "O1", "Sk1", 5);
        service.addRanking("S2", "O2", "Sk1", 7);
        service.addRanking("S3", "O1", "Sk1", 7);
        service.addRanking("S3", "O2", "Sk1", 9);
        // data that should not factor in!
        service.addRanking("S1", "O2", "Sk2", 2);

        Person p = service.findBestPersonFor("Sk1");
        assertEquals(p.getName(), "S3");
    }

}
