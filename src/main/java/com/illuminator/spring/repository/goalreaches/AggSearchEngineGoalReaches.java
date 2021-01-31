package com.illuminator.spring.repository.goalreaches;

import com.illuminator.spring.repository.AggregationInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface AggSearchEngineGoalReaches extends JpaRepository<com.illuminator.entity.goal.AdvEngineGoalReaches, Long> {

    @Query(value = "WITH r AS (SELECT sum(reaches) as count, goalid, sourceid " +
            "FROM search_engine_goal WHERE counterid = :id AND date BETWEEN :startDate " +
            "AND :endDate GROUP BY goalid, sourceid) SELECT r.count, s.name as sourceName, a.name as goalName " +
            "FROM r LEFT JOIN goal a ON r.goalId = a.id " +
            "LEFT JOIN search_engine s ON r.sourceid = s.id ORDER BY goalName, r.count DESC", nativeQuery = true)
    Collection<AggregationInterface> getAggreatedData(
            @Param("id") Long id,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
            );

}

