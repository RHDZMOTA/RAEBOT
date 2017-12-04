package com.iteso.app.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

@SuppressWarnings("all")

public class AppConfigs {
    private static AppConfigs instance = null;
    protected AppConfigs(){}

    private Config conf = ConfigFactory.load();

    /**
     * General configuration for the app.
     */
    public Config app = conf.getConfig("app");

    /**
     * Twilio configuration values (use getString).
     */
    public Config twilioConf = app.getConfig("twilio");

    /**
     * Method to get or create the unique instance of AppConfigs.
     * @return An unique instance of AppConfigs.
     */
    public static AppConfigs getInstance() {
        if (instance == null) {
            instance = new AppConfigs();
        }
        return instance;
    }
}
