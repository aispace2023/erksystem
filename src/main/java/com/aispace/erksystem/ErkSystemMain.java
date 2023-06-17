package com.aispace.erksystem;

import com.aispace.erksystem.service.ServiceManager;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Ai_Space
 */
@Slf4j
public class ErkSystemMain {
    public static void main(String[] args) {
        log.debug("Process Try to Start [{}]", Arrays.toString(args));
        try {
            ServiceManager.startService(args[0]);
        } catch (Exception e) {
            log.error("Err Occurs", e);
        }
    }
}
