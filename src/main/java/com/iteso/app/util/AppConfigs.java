package com.iteso.app.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

@SuppressWarnings("all")

public class AppConfigs {
    private static AppConfigs instance = null;
    private Config conf = ConfigFactory.load();
    public Config app = conf.getConfig("app");
    public Config twilioConf = app.getConfig("twilio");
    protected AppConfigs(){}
    public static AppConfigs getInstance() {
        if (instance == null) {
            instance = new AppConfigs();
        }
        return instance;
    }
}
