package com.aispace.erksystem.config;

import com.aispace.erksystem.common.PasswdUtil;
import com.aispace.erksystem.common.ValidationUtil;
import com.aispace.erksystem.config.base.ConfigValue;
import com.aispace.erksystem.config.base.yaml.YamlConfig;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Created by Ai_Space
 */
@Getter
@ToString
public class UserConfig extends YamlConfig {

    @NotBlank
    @Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$", message = "must be IPv4 address")
    @ConfigValue("rmq.host")
    String rmqHost;
    @NotBlank
    @ConfigValue("rmq.user")
    String rmqUser;
    @NotBlank
    @ConfigValue("rmq.password")
    String rmqPassword;
    @NotBlank
    @ConfigValue("rmq.incoming-queue")
    String incomingQueue;

    @Min(0)
    @ConfigValue("performance.rmq-consumer-count")
    Integer rmqConsumerCount;

    @Override
    public void afterFieldSetting() {
        rmqPassword = PasswdUtil.decrypt(rmqPassword);

        ValidationUtil.validCheck(this);
    }
}
