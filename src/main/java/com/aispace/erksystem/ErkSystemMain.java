package com.aispace.erksystem;

import com.aispace.erksystem.service.ServiceManager;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Created by Ai_Space
 */
@Slf4j
public class ErkSystemMain {
    public static void main(String[] args) {
        log.debug("Process Start [{}]", Arrays.toString(args));
        ServiceManager.getInstance().init(args[0]).loop();
    }
}
