package com.apkzube.bo.repository;

import com.apkzube.bo.entity.AppMst;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppMstRepository extends JpaRepository<AppMst, Long> {}
