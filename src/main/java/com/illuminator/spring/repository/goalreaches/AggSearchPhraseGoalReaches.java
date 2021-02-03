package com.illuminator.spring.repository.goalreaches;

import com.illuminator.spring.repository.GoalAggregationInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface AggSearchPhraseGoalReaches extends JpaRepository<com.illuminator.db.entity.goal.AdvEngineGoalReaches, Long> {

    @Query(value = "SELECT r.count as count, s.name as sourceName, a.name as goalName FROM " +
            "(SELECT sum(reaches) as count, goalid, sourceid " +
            "FROM search_phrase_goal WHERE counterid = :id AND date BETWEEN :startDate " +
            "AND :endDate GROUP BY goalid, sourceid) " +
            "r LEFT JOIN goal a ON r.goalId = a.id " +
            "LEFT JOIN search_phrase s ON r.sourceid = s.id ORDER BY goalName, count DESC", nativeQuery = true)
    Collection<GoalAggregationInterface> getAggreatedData(
            @Param("id") Long id,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}

