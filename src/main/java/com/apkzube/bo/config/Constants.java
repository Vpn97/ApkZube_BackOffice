package com.apkzube.bo.config;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";

    public static final String IMAGE__VALID_REGEX = "([^\\s]+(\\.(?i)(jpe?g|png))$)";
    public static final String ALLOWED_IMAGE_TYPE = "jpg/jpeg/png";

    public static final String SYSTEM = "system";
    public static final String DEFAULT_LANGUAGE = "en";

    public static final String IMAGE_URL_PREFIX_API = "api/asset/image/";
    public static final String ICON_URL_PREFIX_API = "api/asset/icon/";

    public static final String ASSET_IMG_URL = "/api/asset/image/**";
    public static final String ASSET_ICON_URL = "/api/asset/icon/**";

    private Constants() {}
}
