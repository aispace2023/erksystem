// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ErkProvMsg.proto

package com.erksystem.protobuf.prov;

public interface AddServiceProviderInfoRQOrBuilder extends
    // @@protoc_insertion_point(interface_extends:AddServiceProviderInfoRQ)
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
   * <code>string OrgPwd = 2;</code>
   * @return The orgPwd.
   */
  java.lang.String getOrgPwd();
  /**
   * <code>string OrgPwd = 2;</code>
   * @return The bytes for orgPwd.
   */
  com.google.protobuf.ByteString
      getOrgPwdBytes();

  /**
   * <code>string ServiceDuration = 3;</code>
   * @return The serviceDuration.
   */
  java.lang.String getServiceDuration();
  /**
   * <code>string ServiceDuration = 3;</code>
   * @return The bytes for serviceDuration.
   */
  com.google.protobuf.ByteString
      getServiceDurationBytes();

  /**
   * <code>int32 UserNumber = 4;</code>
   * @return The userNumber.
   */
  int getUserNumber();

  /**
   * <code>.ServiceType_e ServiceType = 5;</code>
   * @return The enum numeric value on the wire for serviceType.
   */
  int getServiceTypeValue();
  /**
   * <code>.ServiceType_e ServiceType = 5;</code>
   * @return The serviceType.
   */
  com.erksystem.protobuf.prov.ServiceType_e getServiceType();
}
