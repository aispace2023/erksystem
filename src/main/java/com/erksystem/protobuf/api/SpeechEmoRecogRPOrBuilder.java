// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ErkApiMsg.proto

package com.erksystem.protobuf.api;

public interface SpeechEmoRecogRPOrBuilder extends
    // @@protoc_insertion_point(interface_extends:SpeechEmoRecogRP)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.ErkMsgDataHeader ErkMsgDataHead = 1;</code>
   * @return Whether the erkMsgDataHead field is set.
   */
  boolean hasErkMsgDataHead();
  /**
   * <code>.ErkMsgDataHeader ErkMsgDataHead = 1;</code>
   * @return The erkMsgDataHead.
   */
  com.erksystem.protobuf.api.ErkMsgDataHeader getErkMsgDataHead();
  /**
   * <code>.ErkMsgDataHeader ErkMsgDataHead = 1;</code>
   */
  com.erksystem.protobuf.api.ErkMsgDataHeaderOrBuilder getErkMsgDataHeadOrBuilder();

  /**
   * <code>.ReturnCode_e ReturnCode = 2;</code>
   * @return The enum numeric value on the wire for returnCode.
   */
  int getReturnCodeValue();
  /**
   * <code>.ReturnCode_e ReturnCode = 2;</code>
   * @return The returnCode.
   */
  com.erksystem.protobuf.api.ReturnCode_e getReturnCode();

  /**
   * <code>int64 EmoRecogTime = 3;</code>
   * @return The emoRecogTime.
   */
  long getEmoRecogTime();

  /**
   * <code>.EmotionType_e Emotion = 4;</code>
   * @return The enum numeric value on the wire for emotion.
   */
  int getEmotionValue();
  /**
   * <code>.EmotionType_e Emotion = 4;</code>
   * @return The emotion.
   */
  com.erksystem.protobuf.api.EmotionType_e getEmotion();

  /**
   * <code>float Accuracy = 5;</code>
   * @return The accuracy.
   */
  float getAccuracy();
}
