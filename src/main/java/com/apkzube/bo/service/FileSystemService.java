package com.apkzube.bo.service;

import com.apkzube.bo.config.ApplicationProperties;
import com.apkzube.bo.config.Constants;
import com.apkzube.bo.service.dto.ErrorDTO;
import com.apkzube.bo.util.CommonUtil;
import com.apkzube.bo.util.StringUtil;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class FileSystemService {

    private final Logger log = LoggerFactory.getLogger(FileSystemService.class);

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private Environment env;

    public String getBaseExternalURL() {
        String url = null;
        String protocol = Optional.ofNullable(env.getProperty("server.ssl.key-store")).map(key -> "https").orElse("http");
        String serverPort = env.getProperty("server.port");
        String contextPath = Optional
            .ofNullable(env.getProperty("server.servlet.context-path"))
            .filter(StringUtils::isNotBlank)
            .orElse("/");

        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        url = protocol + "://" + hostAddress + ":" + serverPort + contextPath;

        return url;
    }

    public String getImageURLByName(String name) {
        return getBaseExternalURL() + Constants.IMAGE_URL_PREFIX_API + name;
    }

    public String getIconURLByName(String name) {
        return getBaseExternalURL() + Constants.ICON_URL_PREFIX_API + name;
    }

    public String getIconUrlWithUrlCheck(String iconURL) {
        if (!StringUtil.isNullEmpty(iconURL)) {
            if (CommonUtil.isValidURL(iconURL)) {
                return iconURL;
            }
            return getIconURLByName(iconURL);
        }
        return iconURL;
    }

    public String getImageUrlWithUrlCheck(String imageURL) {
        if (!StringUtil.isNullEmpty(imageURL)) {
            if (CommonUtil.isValidURL(imageURL)) {
                return imageURL;
            }
            return getImageURLByName(imageURL);
        }
        return imageURL;
    }

    public String saveImage(byte[] imageByte, String imageName) throws IOException {
        try {
            String path = applicationProperties.getAsset().getImageDirPath();
            String image = generateImageName(imageName);

            Path newFile = Paths.get(path + image);
            Files.createDirectories(newFile.getParent());

            Files.write(newFile, imageByte);

            //return newFile.toAbsolutePath().toString();
            return image;
        } catch (Exception e) {
            log.error("Error :: saveImage :: " + e.getMessage());
            return null;
        }
    }

    public String saveImage(MultipartFile imageMultipartFile) throws IOException {
        try {
            if (imageMultipartFile != null) {
                String path = applicationProperties.getAsset().getImageDirPath();
                String image = generateImageName(Objects.requireNonNull(imageMultipartFile.getOriginalFilename()));

                Path newFile = Paths.get(path + image);
                Files.createDirectories(newFile.getParent());
                Files.write(newFile, imageMultipartFile.getBytes());

                //return newFile.toAbsolutePath().toString();
                return image;
            }
            return null;
        } catch (Exception e) {
            log.error("Error :: saveImage :: " + e.getMessage());
            return null;
        }
    }

    public String saveIcon(MultipartFile iconMultipartFile) throws IOException {
        try {
            if (iconMultipartFile != null) {
                String path = applicationProperties.getAsset().getIconDirPath();
                String icon = generateIconName(Objects.requireNonNull(iconMultipartFile.getOriginalFilename()));
                Path newFile = Paths.get(path + icon);
                Files.createDirectories(newFile.getParent());

                Files.write(newFile, iconMultipartFile.getBytes());
                log.debug("File Path :: " + newFile.getParent());
                log.debug("File Path :: " + newFile.toAbsolutePath());
                return icon;
            }
            return null;
        } catch (Exception e) {
            log.error("Error :: saveIcon :: " + e.getMessage());
            return null;
        }
    }

    public String saveIcon(byte[] iconByte, String iconName) throws IOException {
        try {
            String path = applicationProperties.getAsset().getIconDirPath();
            String icon = generateIconName(iconName);
            Path newFile = Paths.get(path + icon);
            Files.createDirectories(newFile.getParent());

            Files.write(newFile, iconByte);
            log.debug("File Path :: " + newFile.getParent());
            log.debug("File Path :: " + newFile.toAbsolutePath());

            return icon;
        } catch (Exception e) {
            log.error("Error :: saveIcon :: " + e.getMessage());
            return null;
        }
    }

    public FileSystemResource getImageByName(String name) {
        try {
            String path = applicationProperties.getAsset().getImageDirPath();
            log.debug("File Path :: " + path);
            return new FileSystemResource(Paths.get(path + name));
        } catch (Exception e) {
            log.error("Error :: getImageByName" + e.getMessage(), e);
            throw e;
        }
    }

    public FileSystemResource getIconByName(String name) {
        try {
            String path = applicationProperties.getAsset().getIconDirPath();
            log.debug("File Path :: " + path);
            return new FileSystemResource(Paths.get(path + name));
        } catch (Exception e) {
            log.error("Error :: getIconByName" + e.getMessage(), e);
            throw e;
        }
    }

    public String generateIconName(String postfix) {
        postfix = postfix.trim().toLowerCase();

        String ext = postfix.substring(postfix.lastIndexOf("."));
        String name = postfix.substring(0, postfix.lastIndexOf(".")).replaceAll("[^A-Za-z0-9]", "_");
        String buffer = "icon_" + name + "_" + new Date().getTime() + ext;

        return buffer;
    }

    public String generateImageName(String postfix) {
        postfix = postfix.trim().toLowerCase();

        String ext = postfix.substring(postfix.lastIndexOf("."));
        String name = postfix.substring(0, postfix.lastIndexOf(".")).replaceAll("[^A-Za-z0-9]", "_");
        String buffer = "image_" + name + "_" + new Date().getTime() + ext;

        return buffer;
    }

    public boolean validateImage(MultipartFile multipartFile) {
        // Regex to check valid image file extension.;

        // Compile the ReGex
        Pattern p = Pattern.compile(Constants.IMAGE__VALID_REGEX);

        // If the string is empty
        // return false
        if (multipartFile == null) {
            return false;
        }

        String name = multipartFile.getOriginalFilename();

        // Pattern class contains matcher() method
        // to find matching between given string
        // and regular expression.
        if (name == null) {
            return false;
        }
        name = "image" + name.substring(name.lastIndexOf("."));
        Matcher m = p.matcher(name);

        // Return if the string
        // matched the ReGex
        return m.matches();
    }

    public List<ErrorDTO> validateImageWithError(MultipartFile multipartFile) {
        List<ErrorDTO> errorDTOList = new ArrayList<>();

        if (multipartFile != null) {
            if (!validateImage(multipartFile)) {
                errorDTOList.add(new ErrorDTO(messageSource.getMessage("invalid.image.type", null, Locale.getDefault())));
            }
        }
        return errorDTOList;
    }

    public List<ErrorDTO> validateIconWithError(MultipartFile multipartFile) {
        List<ErrorDTO> errorDTOList = new ArrayList<>();

        if (multipartFile != null) {
            if (!validateImage(multipartFile)) {
                errorDTOList.add(new ErrorDTO(messageSource.getMessage("invalid.icon.type", null, Locale.getDefault())));
            }
        }

        return errorDTOList;
    }
}
