package com.apkzube.bo.repository;

import com.apkzube.bo.entity.ProgrammingLangMst;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammingLangMstRepository extends JpaRepository<ProgrammingLangMst, Long> {
    ProgrammingLangMst findOneByLangId(Long langId);
}
