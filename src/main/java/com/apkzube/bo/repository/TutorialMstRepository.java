package com.apkzube.bo.repository;

import com.apkzube.bo.entity.TutorialMst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TutorialMstRepository extends JpaRepository<TutorialMst, Long> {
    @Query(
        value = "SELECT COUNT(tm.tut_mst_id) FROM tutorial_mst tm," +
        " tutorial_category_mst tc WHERE tm.tut_cat_mst_id=tc.tut_cat_mst_id " +
        "AND tm.is_active=1 " +
        "AND tc.is_active=1  " +
        "AND tc.app_id=:appId",
        nativeQuery = true
    )
    int totalTutorial(@Param("appId") long appId);
}
