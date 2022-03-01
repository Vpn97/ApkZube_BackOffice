package com.apkzube.bo.web.rest.controller;

import com.apkzube.bo.entity.AppMst;
import com.apkzube.bo.service.dto.AppMstDTO;
import com.apkzube.bo.service.mapper.MapperService;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/testController")
public class TestController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MapperService mapperService;

    @GetMapping("/getAllAppMst")
    public List<AppMstDTO> getAllAppMst() {
        return this.entityManager.createQuery("FROM AppMst", AppMst.class)
            .getResultList()
            .stream()
            .map(mapperService::appMstToDTO)
            .collect(Collectors.toList());
    }
}
