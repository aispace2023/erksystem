package com.aispace.erksystem.rmq.module;

import com.aispace.erksystem.config.UserConfig;
import com.aispace.erksystem.rmq.RmqManager;
import com.aispace.erksystem.service.AppInstance;
import com.aispace.erksystem.service.DBManager;
import com.erksystem.protobuf.api.ErkApiMsg;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import static com.aispace.erksystem.rmq.module.RmqLogPrinter.proto2Json;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * @author kangmoo Heo
 */
@Slf4j
public class RmqSimBase {
    private static final String CONFIG_FILE_NAME = "user_config.yaml";
    private static final String PROCESS_NAME = "erk_service";
    private static final AppInstance appInstance = AppInstance.getInstance();
    private static final UserConfig config = appInstance.getUserConfig();
    public static final List<ErkApiMsg> sendMsgList = new ArrayList<>();
    public static byte[] lastSendRmqMsgByte;
    public static ErkApiMsg lastSendRmqMsg;
    public static Consumer<ErkApiMsg> onRmqMsgResponse = msg -> {
    };

    public static AtomicBoolean isInitDone = new AtomicBoolean(false);

    public static void init(String configPath) throws IOException, NoSuchFieldException {
        if (!isInitDone.compareAndSet(false, true)) return;

        config.init(Path.of(configPath).resolve(CONFIG_FILE_NAME).toString());

        RmqManager rmqManager = RmqManager.getInstance();
        Field rmqModuleField = rmqManager.getClass().getDeclaredField("rmqModule");
        rmqModuleField.setAccessible(true);
        try {
            rmqModuleField.set(rmqManager, getRmqModuleMock());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            rmqModuleField.setAccessible(false);
        }

        DBManager.getInstance().start();
    }

    public static RmqModule getRmqModuleMock() throws IOException {
        RmqModule rmqModule = mock(RmqModule.class);
        when(rmqModule.getChannel()).thenReturn(null);
        doAnswer(invocationOnMock -> {
            synchronized (sendMsgList) {
                lastSendRmqMsgByte = invocationOnMock.getArgument(1);
                lastSendRmqMsg = ErkApiMsg.parseFrom(lastSendRmqMsgByte);
                sendMsgList.add(lastSendRmqMsg);
                onRmqMsgResponse.accept(lastSendRmqMsg);
            }
            return null;
        }).when(rmqModule).sendMessage(anyString(), any(byte[].class), anyInt());

        Channel channel = mock(Channel.class);
        when(channel.queueDeclare(anyString(), anyBoolean(), anyBoolean(), anyBoolean(), anyMap())).thenReturn(null);
        when(channel.queueDelete(anyString())).thenReturn(null);
        when(rmqModule.getChannel()).thenReturn(channel);

        return rmqModule;
    }

    public static void handleMessage(ErkApiMsg message) {
        switch (message.getMsgCase()) {
            case ERKENGINECREATERP, ERKENGINEDELETERP -> handleSubsystemApiMessage(message);
            default -> handleApiMessage(message);
        }
    }

    private static void handleApiMessage(ErkApiMsg message) {
        handleApiMessage(message.toByteArray());
    }

    private static void handleApiMessage(byte[] message) {
        ErkApiMsgRmqConsumer.consumeApiMessage(message);
    }

    private static void handleSubsystemApiMessage(ErkApiMsg message) {
        handleSubsystemApiMessage(message.toByteArray());
    }

    private static void handleSubsystemApiMessage(byte[] message) {
        ErkApiMsgRmqConsumer.consumeSubsystemApiMessage(message);
    }
}
