// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ErkApiMsg.proto

package com.erksystem.protobuf.api;

public interface HB_RQOrBuilder extends
    // @@protoc_insertion_point(interface_extends:HB_RQ)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.ErkMsgHeader ErkMsgHead = 1;</code>
   * @return Whether the erkMsgHead field is set.
   */
  boolean hasErkMsgHead();
  /**
   * <code>.ErkMsgHeader ErkMsgHead = 1;</code>
   * @return The erkMsgHead.
   */
  com.erksystem.protobuf.api.ErkMsgHeader getErkMsgHead();
  /**
   * <code>.ErkMsgHeader ErkMsgHead = 1;</code>
   */
  com.erksystem.protobuf.api.ErkMsgHeaderOrBuilder getErkMsgHeadOrBuilder();

  /**
   * <code>string Queue_id = 2;</code>
   * @return The queueId.
   */
  java.lang.String getQueueId();
  /**
   * <code>string Queue_id = 2;</code>
   * @return The bytes for queueId.
   */
  com.google.protobuf.ByteString
      getQueueIdBytes();
}
