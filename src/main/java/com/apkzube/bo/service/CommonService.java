package com.apkzube.bo.service;

import com.apkzube.bo.config.ApplicationProperties;
import com.apkzube.bo.util.CommonUtil;
import liquibase.pro.packaged.S;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommonService {

    private final Logger log = LoggerFactory.getLogger(CommonService.class);

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private FileSystemService fileSystemService;

    public String getIconURL(String iconUrl) {
        try {
            if (CommonUtil.isValidURL(iconUrl)) {
                return iconUrl;
            } else {
                return fileSystemService.getIconURLByName(iconUrl);
            }
        } catch (Exception e) {
            log.error("Error at CommonService :: getIconURL :: " + e.getMessage());
            return iconUrl;
        }
    }

    public String getImageURL(String imageURL) {
        try {
            if (CommonUtil.isValidURL(imageURL)) {
                return imageURL;
            } else {
                return fileSystemService.getImageURLByName(imageURL);
            }
        } catch (Exception e) {
            log.error("Error at CommonService :: getImageURL :: " + e.getMessage());
            return imageURL;
        }
    }
}
