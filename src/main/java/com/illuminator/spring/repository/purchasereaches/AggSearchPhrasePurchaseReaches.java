package com.illuminator.spring.repository.purchasereaches;

import com.illuminator.spring.repository.ViewAggregationInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface AggSearchPhrasePurchaseReaches extends JpaRepository<com.illuminator.db.entity.goal.AdvEngineGoalReaches, Long> {

    @Query(value = "SELECT r.count as count, s.name as sourceName FROM " +
            "(SELECT sum(reaches) as count, sourceid " +
            "FROM search_phrase_purchases WHERE counterid = :id AND date BETWEEN :startDate " +
            "AND :endDate GROUP BY sourceid) " +
            "r LEFT JOIN search_phrase s ON r.sourceid = s.id ORDER BY count DESC", nativeQuery = true)
    Collection<ViewAggregationInterface> getAggreatedData(
            @Param("id") Long id,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}

