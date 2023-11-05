package com.aispace.erksystem.service;

import com.aispace.erksystem.config.UserConfig;
import lombok.Data;

/**
 * Created by Ai_Space
 */
@Data
public class AppInstance {
    private static final AppInstance INSTANCE = new AppInstance();
    private UserConfig userConfig = new UserConfig();

    private AppInstance() {
    }

    public static AppInstance getInstance() {
        return INSTANCE;
    }
}
