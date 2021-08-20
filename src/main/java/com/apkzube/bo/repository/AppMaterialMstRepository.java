package com.apkzube.bo.repository;

import com.apkzube.bo.entity.AppMaterialMst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppMaterialMstRepository extends JpaRepository<AppMaterialMst, Long> {
    @Query(countQuery = "SELECT COUNT(*) FROM AppMaterialMst m WHERE m.appId=:appId")
    int countByAppId(@Param("appId") long appId);
}
