package com.aispace.erksystem.config.base.ini;

import lombok.extern.slf4j.Slf4j;
import com.aispace.erksystem.config.base.ConfigReader;

import java.io.IOException;

/**
 * Created by Ai_Space
 */
@Slf4j
public class IniConfig extends ConfigReader {

    @Override
    protected final void fieldSetting() throws IOException, NoSuchFieldException {
        IniConfigInjector.inject(this, filePath.toString());
    }
}
