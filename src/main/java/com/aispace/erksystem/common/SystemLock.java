package com.aispace.erksystem.common;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;

import static java.nio.file.StandardOpenOption.*;

/**
 * Created by Ai_Space
 */
@Slf4j
public class SystemLock {

    private SystemLock() {
    }

    public static void lock(String keyFile) throws IOException, InterruptedException {
        String tmpdir = System.getProperty("java.io.tmpdir");
        File f = new File(tmpdir, System.getProperty("lock_file", keyFile));
        try {
            try (FileChannel fileChannel = FileChannel.open(f.toPath(), CREATE, READ, WRITE)) {
                FileLock lock = fileChannel.tryLock();
                if (lock != null) {
                    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                        try {
                            lock.release();
                            fileChannel.close();
                            Files.delete(f.toPath());
                        } catch (IOException e) {
                            //ignore
                        }
                    }));
                } else {
                    log.error("Process already running");
                    Thread.sleep(500L);
                    System.exit(1);
                }
            }
        } catch (Exception e) {
            log.error("SystemLock exception occurs.", e);
            throw e;
        }
    }
}
