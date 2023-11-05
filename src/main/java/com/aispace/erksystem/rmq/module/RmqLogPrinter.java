package com.aispace.erksystem.rmq.module;

import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * Created by Ai_Space
 */
@Slf4j
public class RmqLogPrinter {
    private RmqLogPrinter() {
    }

    private static final JsonFormat.Printer jsonPrinter = JsonFormat.printer().includingDefaultValueFields();

    public static Optional<String> proto2Json(Message msg) {
        try {
            return Optional.of(jsonPrinter.print(msg));
        } catch (Exception e) {
            log.warn("Err Occurs", e);
            return Optional.empty();
        }
    }

}
