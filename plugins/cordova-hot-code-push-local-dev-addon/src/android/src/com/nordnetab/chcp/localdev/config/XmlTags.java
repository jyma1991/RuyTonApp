package com.nordnetab.chcp.localdev.config;

/**
 * Created by Nikolay Demyankov on 03.09.15.
 *
 * Plugin specific xml keys and attributes in config.xml
 */
final class XmlTags {

    private XmlTags() {
    }

    public static final String MAIN_TAG = "chcp";

    // keys for application config file url
    public static final String CONFIG_FILE_TAG = "config-file";
    public static final String CONFIG_FILE_URL_ATTRIBUTE = "url";

    // keys for local development
    public static final String LOCAL_DEVELOPMENT_TAG = "local-development";
    public static final String LOCAL_DEVELOPMENT_ENABLED_ATTRIBUTE = "enabled";
}
