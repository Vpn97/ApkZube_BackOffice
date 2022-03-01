package com.apkzube.bo.repository;

import com.apkzube.bo.entity.ActionCodeMst;
import com.apkzube.bo.entity.AppMaterialType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppMaterialTypeRepository extends JpaRepository<AppMaterialType, Long> {
    AppMaterialType findOneByTypeCode(String typeCode);

    List<AppMaterialType> findAllByTypeCode(String typeCode);
}
