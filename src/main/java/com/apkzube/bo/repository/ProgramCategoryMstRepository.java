package com.apkzube.bo.repository;

import com.apkzube.bo.entity.ProgramCategoryMst;
import com.apkzube.bo.service.dto.ProgramCategoryDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProgramCategoryMstRepository extends JpaRepository<ProgramCategoryMst, Long> {
    @Query(countQuery = "SELECT COUNT(*) FROM ProgramCategoryMst pc WHERE pc.appId=:appId")
    int countByAppId(@Param("appId") long appId);

    List<ProgramCategoryMst> findAllByAppId(Long appId);

    ProgramCategoryMst findOneByProgramCatId(Long catId);

    int countByCatName(String catName);
}
