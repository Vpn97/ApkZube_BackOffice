package com.apkzube.bo.repository;

import com.apkzube.bo.entity.TutorialCategoryMst;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TutorialCategoryMstRepository extends JpaRepository<TutorialCategoryMst, Long> {
    @Query(countQuery = "SELECT COUNT(*) FROM TutorialCategoryMst tc WHERE tc.isActive=1 AND tc.appId=:appId")
    int countByAppId(@Param("appId") Long appId);

    List<TutorialCategoryMst> findAllByAppId(long appId);

    List<TutorialCategoryMst> findAllByAppIdAndIsActive(long appId, boolean i);

    TutorialCategoryMst findOneByTutCatMstId(long catId);

    @Query(
        countQuery = "SELECT COUNT(*) FROM TutorialCategoryMst tc WHERE tc.isActive=1 AND tc.categoryName=:categoryName  AND tc.appId=:appId "
    )
    int countByCategoryNameAndAppId(@Param("categoryName") String categoryName, @Param("appId") Long appId);

    List<TutorialCategoryMst> findAllByAppIdAndCatType(long appId, String tutorial);
}
