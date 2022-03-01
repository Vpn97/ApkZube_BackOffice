package com.apkzube.bo.repository;

import com.apkzube.bo.entity.TutorialDtl;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialDtlRepository extends JpaRepository<TutorialDtl, Long> {
    List<TutorialDtl> findAllByTutMstId(Long mstId);
}
