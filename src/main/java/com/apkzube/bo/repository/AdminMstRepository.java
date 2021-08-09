package com.apkzube.bo.repository;

import com.apkzube.bo.entity.AdminMst;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminMstRepository extends JpaRepository<AdminMst, Long> {
    Optional<AdminMst> findOneWithByEmailIdIgnoreCase(String login);

    Optional<AdminMst> findOneWithByMobileNum(String mobileNum);
}
