package com.apkzube.bo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Apk Zube Back Office.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link tech.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    public final Asset asset = new Asset();

    public static class Asset {

        private String iconDirPath;
        private String imageDirPath;

        public String getIconDirPath() {
            return iconDirPath;
        }

        public void setIconDirPath(String iconDirPath) {
            this.iconDirPath = iconDirPath;
        }

        public String getImageDirPath() {
            return imageDirPath;
        }

        public void setImageDirPath(String imageDirPath) {
            this.imageDirPath = imageDirPath;
        }
    }

    public Asset getAsset() {
        return asset;
    }
}
