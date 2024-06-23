package com.aispace.erksystem.rmq.handler.base;

import com.erksystem.protobuf.api.ErkApiMsg;
import com.google.protobuf.Message;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Ai_Space
 */
@Slf4j
public class ErkMsgWrapper {
    private ErkMsgWrapper() {
    }

    public static ErkApiMsg wrap2ErkApiMsg(Message message) {
        ErkApiMsg.Builder builder = ErkApiMsg.newBuilder();
        try {
            Method method = Arrays.stream(builder.getClass().getDeclaredMethods())
                    .filter(o -> o.getName().startsWith("set"))
                    .filter(o -> o.getParameterTypes().length == 1)
                    .filter(o -> o.getParameterTypes()[0].equals(message.getClass()))
                    .findAny()
                    .orElseThrow();
            method.invoke(builder, message);
        } catch (Exception e) {
            throw new IllegalArgumentException("Fail to wrap2ErkApiMsg");
        }

        return builder.build();
    }
}
