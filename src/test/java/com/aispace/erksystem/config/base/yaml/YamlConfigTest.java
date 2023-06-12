package com.aispace.erksystem.config.base.yaml;

import com.aispace.erksystem.config.UserConfig;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Created by Ai_Space
 */
class YamlConfigTest {
    @Test
    void configReadTest() throws IOException, NoSuchFieldException {
        UserConfig config = new UserConfig();
        config.init(getClass().getClassLoader().getResource("config/test.yaml").getPath());

        System.out.println(config);
    }
}