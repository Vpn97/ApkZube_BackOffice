package com.apkzube.bo.repository;

import com.apkzube.bo.entity.ProgramCategoryMst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProgramCategoryMstRepository extends JpaRepository<ProgramCategoryMst, Long> {
    @Query(countQuery = "SELECT COUNT(*) FROM ProgramCategoryMst pc WHERE pc.appId=:appId")
    int countByAppId(@Param("appId") long appId);
}
