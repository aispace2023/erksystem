package com.aispace.erksystem.rmq;

import com.erksystem.protobuf.api.*;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;

import java.util.Optional;

public class ErkMsgUtil {
    private ErkMsgUtil() {
    }

    /**
     * Message로부터 QueueInfo 정보를 파싱하여 반환한다
     *
     * @param message QueueInfo 정보를 포함한 메시지
     * @return QueueInfo 정보
     */
    public static Optional<QueueInfo_s> getQueueInfo(Message message) {
        try {
            if (message instanceof ErkApiMsg erkApiMsg) {
                message = getMsgFromErkApiMsg(erkApiMsg).orElseThrow();
            }
            Message msgBody = message instanceof ErkApiMsg erkApiMsg ? getMsgFromErkApiMsg(erkApiMsg).orElseThrow() : message;
            return getSpecificTypeFromMessage(msgBody, QueueInfo_s.class)
                    .or(() -> getSpecificTypeFromMessage(msgBody, ErkMsgHead_s.class).map(ErkMsgHead_s::getQueueInfo))
                    .or(() -> getSpecificTypeFromMessage(msgBody, ErkMsgDataHead_s.class).map(ErkMsgDataHead_s::getQueueInfo));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * Message에 QueueInfo 정보를 설정하여 반환한다.
     *
     * @param message   메시지
     * @param queueInfo QueueInfo 정보
     * @return QueueInfo 정보가 설정된 메시지
     */
    public static Message setQueueInfo(Message message, QueueInfo_s queueInfo) {
        return message instanceof ErkApiMsg erkApiMsg
                ? setMsgInErkApiMsg(erkApiMsg, setQueueInfo2(getMsgFromErkApiMsg(erkApiMsg).orElseThrow(), queueInfo))
                : setQueueInfo2(message, queueInfo);
    }

    private static Message setQueueInfo2(Message message, QueueInfo_s queueInfo) {
        return getSpecificTypeFromMessage(message, ErkMsgHead_s.class)
                .map(o -> setSpecificTypeFromMessage(message, ErkMsgHead_s.class, o.toBuilder().setQueueInfo(queueInfo).build()))
                .orElseGet(() -> getSpecificTypeFromMessage(message, ErkMsgDataHead_s.class)
                        .map(o -> setSpecificTypeFromMessage(message, ErkMsgDataHead_s.class, o.toBuilder().setQueueInfo(queueInfo).build()))
                        .orElseGet(() -> setSpecificTypeFromMessage(message, QueueInfo_s.class, queueInfo)));
    }

    /**
     * Message로부터 특정 타입의 Message를 반환한다. 주로 QueueInfo_s, ErkMsgHead_s, ErkMsgDataHead_s를 추출할 때 사용한다.
     * @param message 메시지
     * @param type   반환할 메시지 타입
     * @return 특정 타입의 Message
     * @param <T> 메시지 타입
     */
    private static <T extends Message> Optional<T> getSpecificTypeFromMessage(Message message, Class<T> type) {
        for (Descriptors.FieldDescriptor fieldDescriptor : message.getDescriptorForType().getFields()) {
            if (message.hasField(fieldDescriptor)
                    && fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE
                    && fieldDescriptor.getMessageType().getFullName().equals(type.getSimpleName())) {
                return Optional.of(type.cast(message.getField(fieldDescriptor)));
            }
        }
        return Optional.empty();
    }

    /**
     * Message에 특정 타입의 Message를 설정하여 반환한다
     * @param message 메시지
     * @param type 설정할 메시지 타입
     * @param value 설정할 메시지
     * @return 설정된 메시지
     * @param <T> 메시지 타입
     */
    private static <T extends Message> Message setSpecificTypeFromMessage(Message message, Class<T> type, T value) {
        Message.Builder builder = message.toBuilder();
        Descriptors.FieldDescriptor specificTypeFieldDescriptor = null;
        for (Descriptors.FieldDescriptor fieldDescriptor : builder.getDescriptorForType().getFields()) {
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE
                    && fieldDescriptor.getMessageType().getFullName().equals(type.getSimpleName())) {
                specificTypeFieldDescriptor = fieldDescriptor;
                break;
            }
        }
        if (specificTypeFieldDescriptor != null) {
            builder.setField(specificTypeFieldDescriptor, value);
        }
        return builder.build();
    }

    private static Optional<Message> getMsgFromErkApiMsg(ErkApiMsg erkApiMsg) {
        for (Descriptors.FieldDescriptor fieldDescriptor : erkApiMsg.getDescriptorForType().getFields()) {
            if (erkApiMsg.hasField(fieldDescriptor)) {
                return Optional.of((Message) erkApiMsg.getField(fieldDescriptor));
            }
        }
        return Optional.empty();
    }

    private static ErkApiMsg setMsgInErkApiMsg(ErkApiMsg erkApiMsg, Message message) {
        ErkApiMsg.Builder builder = erkApiMsg.toBuilder();
        for (Descriptors.FieldDescriptor fieldDescriptor : erkApiMsg.getDescriptorForType().getFields()) {
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE
                    && fieldDescriptor.getMessageType().getFullName().equals(message.getDescriptorForType().getFullName())) {
                builder.setField(fieldDescriptor, message);
                break;
            }
        }
        return builder.build();
    }
}
