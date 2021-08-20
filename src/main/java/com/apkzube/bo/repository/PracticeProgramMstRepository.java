package com.apkzube.bo.repository;

import com.apkzube.bo.entity.PracticeProgramMst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PracticeProgramMstRepository extends JpaRepository<PracticeProgramMst, Long> {
    @Query(countQuery = "SELECT COUNT(*) FROM PracticeProgramMst pm WHERE pm.appId=:appId")
    int countByAppId(@Param("appId") long appId);
}
