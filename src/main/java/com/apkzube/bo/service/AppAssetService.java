package com.apkzube.bo.service;

import com.apkzube.bo.repository.AppAssetMstRepository;
import com.apkzube.bo.repository.AppMstRepository;
import com.apkzube.bo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppAssetService {

    private final Logger log = LoggerFactory.getLogger(AppAssetService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AppMstRepository appMstRepository;

    @Autowired
    private AppAssetMstRepository appAssetMstRepository;
}
