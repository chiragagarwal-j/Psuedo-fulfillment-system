package com.pfsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pfsystem.entities.RechargePlans;

public interface RechargePlansRepository extends JpaRepository<RechargePlans, Long> {

    @Query("SELECT n FROM RechargePlans n WHERE n.operator = :operator AND n.categories LIKE %:categoryName%")
    List<RechargePlans> findPlanDetailsByOperatorAndCategory(@Param("operator") String operator,
            @Param("categoryName") String categoryName);

}