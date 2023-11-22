package com.aispace.erksystem.service.database.type;

public enum ServiceType {
    unknown(0), physiology(1), speech(2),
    face(3), physiology_speech(4), physiology_face(5),
    speech_face(6), physiology_speech_face(7), knowledge(8),
    all(9), reserved1(10), reserved2(11);

    private final int value;
    ServiceType(int value) { this.value = value; }

    public int getValue() {
        return value;
    }

    public static String getName(int value) {
        for (ServiceType type : values()) {
            if (type.value == value) {
                return type.name();
            }
        }
        return unknown.name();
    }
}
