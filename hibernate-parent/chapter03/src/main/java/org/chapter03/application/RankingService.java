
package org.chapter03.application;

import java.util.Map;

import org.chapter03.hibernate.entity.Person;

public interface RankingService {
   int getRankingFor(String subject, String skill);

   void addRanking(String subject, String observer, String skill, int ranking);

   void updateRanking(String subject, String observer, String skill, int ranking);

   void removeRanking(String subject, String observer, String skill);

   Map<String, Integer> findRankingsFor(String subject);

   Person findBestPersonFor(String skill);
}
