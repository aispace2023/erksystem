// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ErkApiMsg.proto

// Protobuf Java Version: 3.25.0
package com.erksystem.protobuf.api;

public interface PhysioEmoRecogRQ_mOrBuilder extends
    // @@protoc_insertion_point(interface_extends:PhysioEmoRecogRQ_m)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.ErkMsgDataHead_s ErkMsgDataHead = 1;</code>
   * @return Whether the erkMsgDataHead field is set.
   */
  boolean hasErkMsgDataHead();
  /**
   * <code>.ErkMsgDataHead_s ErkMsgDataHead = 1;</code>
   * @return The erkMsgDataHead.
   */
  com.erksystem.protobuf.api.ErkMsgDataHead_s getErkMsgDataHead();
  /**
   * <code>.ErkMsgDataHead_s ErkMsgDataHead = 1;</code>
   */
  com.erksystem.protobuf.api.ErkMsgDataHead_sOrBuilder getErkMsgDataHeadOrBuilder();

  /**
   * <code>int64 DataTimeStamp = 2;</code>
   * @return The dataTimeStamp.
   */
  long getDataTimeStamp();

  /**
   * <code>int32 MsgDataLength = 3;</code>
   * @return The msgDataLength.
   */
  int getMsgDataLength();

  /**
   * <code>bytes MsgDataFrame = 4;</code>
   * @return The msgDataFrame.
   */
  com.google.protobuf.ByteString getMsgDataFrame();
}
