package com.aispace.erksystem.config;

import com.aispace.erksystem.config.base.ConfigValue;
import com.aispace.erksystem.config.base.yaml.YamlConfig;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by Ai_Space
 */
@Getter
@ToString
public class UserConfig extends YamlConfig {
    @ConfigValue("rmq.host")
    String rmqHost;
    @ConfigValue("rmq.user")
    String rmqUser;
    @ConfigValue("rmq.password")
    String rmqPassword;
}
