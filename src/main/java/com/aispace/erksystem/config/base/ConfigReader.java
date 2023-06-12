package com.aispace.erksystem.config.base;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by Ai_Space
 */
@Slf4j
public abstract class ConfigReader {
    private FileWatcher fileWatcher;
    @Getter
    protected Path filePath;

    /**
     * Config 파일 설정 및 필드값 세팅
     */
    public ConfigReader init(String filePath) throws IOException, NoSuchFieldException {
        if (this.filePath != null) {
            log.warn("Config Already initiated");
            return this;
        }

        this.filePath = Path.of(filePath);
        fieldSetting();
        afterFieldSetting();
        log.info("Config init done");
        return this;
    }

    protected abstract void fieldSetting() throws IOException, NoSuchFieldException;

    /**
     * 필드값 주입 이후에 수행되는 메서드.
     * 필드값 세팅 후 추가적인 동작이 필요한 경우 오버라이딩하여 사용.
     */
    protected void afterFieldSetting() {
    }

    /**
     * 해당 메서드 호출 시, config 파일 변경을 감지하고, 파일 변경이 감지될 경우 파일을 읽고 세팅하는 로직이 수행
     * @return
     */
    public ConfigReader startWatch() {
        if (this.fileWatcher != null) {
            log.warn("Config Already Watching");
            return this;
        }
        this.fileWatcher = new FileWatcher(filePath);
        this.fileWatcher.startWatch(() -> {
            try {
                fieldSetting();
                afterFieldSetting();
            } catch (Exception e) {
                log.warn("Err Occurs", e);
            }
        });
        log.info("Config Watch Start");
        return this;
    }

    /**
     * 해당 메서드 호출 시, 더 이상 config 파일 변경을 감지하지 않음
     * @return
     */
    public ConfigReader stopWatch() {
        if (this.fileWatcher == null) {
            log.warn("Config Already Not Watching");
            return this;
        }
        this.fileWatcher.stopWatching();
        log.info("Config Watch Stop");
        return this;
    }
}
