// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ErkProvMsg.proto

package com.erksystem.protobuf.prov;

public interface DelUserInfoRPOrBuilder extends
    // @@protoc_insertion_point(interface_extends:DelUserInfoRP)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string OrgName = 1;</code>
   * @return The orgName.
   */
  java.lang.String getOrgName();
  /**
   * <code>string OrgName = 1;</code>
   * @return The bytes for orgName.
   */
  com.google.protobuf.ByteString
      getOrgNameBytes();

  /**
   * <code>string UserName = 2;</code>
   * @return The userName.
   */
  java.lang.String getUserName();
  /**
   * <code>string UserName = 2;</code>
   * @return The bytes for userName.
   */
  com.google.protobuf.ByteString
      getUserNameBytes();

  /**
   * <code>.UserProfileResult_e ResultType = 3;</code>
   * @return The enum numeric value on the wire for resultType.
   */
  int getResultTypeValue();
  /**
   * <code>.UserProfileResult_e ResultType = 3;</code>
   * @return The resultType.
   */
  com.erksystem.protobuf.prov.UserProfileResult_e getResultType();

  /**
   * <code>string Return = 4;</code>
   * @return The return.
   */
  java.lang.String getReturn();
  /**
   * <code>string Return = 4;</code>
   * @return The bytes for return.
   */
  com.google.protobuf.ByteString
      getReturnBytes();
}
