package com.nordnetab.chcp.localdev.config;

import android.content.Context;

import org.apache.cordova.ConfigXmlParser;
import org.xmlpull.v1.XmlPullParser;

/**
 * Created by Nikolay Demyankov on 06.08.15.
 * <p/>
 * XML parser for Cordova's config.xml.
 * Used to read plugin specific preferences.
 *
 * @see com.nordnetab.chcp.main.config.ChcpXmlConfig
 */
class ChcpXmlConfigParser extends ConfigXmlParser {

    private ChcpXmlConfig chcpConfig;

    private boolean isInsideChcpBlock;
    private boolean didParseChcpBlock;

    /**
     * Parse config.xml.
     * Result is set into passed ChcpXmlConfig instance.
     *
     * @param context    current context
     * @param chcpConfig config instance to which we will set preferences from config.xml
     * @see com.nordnetab.chcp.main.config.ChcpXmlConfig
     */
    public void parse(Context context, ChcpXmlConfig chcpConfig) {
        this.chcpConfig = chcpConfig;

        isInsideChcpBlock = false;
        didParseChcpBlock = false;

        super.parse(context);
    }

    @Override
    public void handleStartTag(XmlPullParser xml) {
        if (didParseChcpBlock) {
          return;
        }

        final String name = xml.getName();
        if (name.equals(XmlTags.MAIN_TAG)) {
            isInsideChcpBlock = true;
            return;
        }

        // proceed only if we are parsing our plugin preferences
        if (!isInsideChcpBlock) {
            return;
        }

        // parse local development options
        if (name.equals(XmlTags.LOCAL_DEVELOPMENT_TAG)) {
            processLocalDevelopmentBlock(xml);
            return;
        }

        // parse configuration file preference
        if (name.equals(XmlTags.CONFIG_FILE_TAG)) {
            processConfigFileBlock(xml);
        }
    }

    private void processLocalDevelopmentBlock(XmlPullParser xml) {
        boolean isDevModeEnabled = xml.getAttributeValue(null, XmlTags.LOCAL_DEVELOPMENT_ENABLED_ATTRIBUTE).equals("true");
        chcpConfig.getDevelopmentOptions().setEnabled(isDevModeEnabled);
    }

    private void processConfigFileBlock(XmlPullParser xml) {
        String configUrl = xml.getAttributeValue(null, XmlTags.CONFIG_FILE_URL_ATTRIBUTE);

        chcpConfig.setConfigUrl(configUrl);
    }

    @Override
    public void handleEndTag(XmlPullParser xml) {
        if (didParseChcpBlock) {
            return;
        }

        String name = xml.getName();
        if (name.equals(XmlTags.MAIN_TAG)) {
            didParseChcpBlock = true;
            isInsideChcpBlock = false;
        }
    }
}
