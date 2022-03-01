package com.apkzube.bo.repository;

import com.apkzube.bo.entity.ActionCodeMst;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionCodeMstRepository extends JpaRepository<ActionCodeMst, Long> {
    List<ActionCodeMst> findAllByActionCode(String clickActionCode);
}
