package com.aispace.erksystem.service;

import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.rmq.module.RmqModule;
import lombok.Data;

/**
 * Created by Ai_Space
 */
@Data
public class AppInstance {
    private static final AppInstance INSTANCE = new AppInstance();
    public static final String SEND_QUEUE_NAME_PATTERN = "SEND_%02d_%03d_%03d"; // SEND_{2자리 : enum EngineType_e }_{3자리 : OrgId}_{3자리 : UserId}
    public static final String RECV_QUEUE_NAME_PATTERN = "RECV_%02d_%03d_%03d"; // RECV_{2자리 : enum EngineType_e }_{3자리 : OrgId}_{3자리 : UserId}

    private UserConfig userConfig = new UserConfig();
    private RmqModule rmqModule;

    private AppInstance() {
    }

    public static AppInstance getInstance() {
        return INSTANCE;
    }
}
