// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ErkApiMsg.proto

// Protobuf Java Version: 3.25.0
package com.erksystem.protobuf.api;

/**
 * Protobuf enum {@code UserProfileResult_e}
 */
public enum UserProfileResult_e
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>User_ProfileUSER_PROFILE_UNKNOWN = 0;</code>
   */
  User_ProfileUSER_PROFILE_UNKNOWN(0),
  /**
   * <code>User_Profile_Ok = 1;</code>
   */
  User_Profile_Ok(1),
  /**
   * <code>User_Profile_Nok_OrgName = 2;</code>
   */
  User_Profile_Nok_OrgName(2),
  /**
   * <code>User_Profile_Nok_UserName = 3;</code>
   */
  User_Profile_Nok_UserName(3),
  /**
   * <code>User_Profile_Nok_UserPwd = 4;</code>
   */
  User_Profile_Nok_UserPwd(4),
  /**
   * <code>User_Profile_Nok_ServiceDuration = 5;</code>
   */
  User_Profile_Nok_ServiceDuration(5),
  /**
   * <code>User_Profile_Nok_Age = 6;</code>
   */
  User_Profile_Nok_Age(6),
  /**
   * <code>User_Profile_Nok_Sex = 7;</code>
   */
  User_Profile_Nok_Sex(7),
  /**
   * <code>User_Profile_Nok_UserType = 8;</code>
   */
  User_Profile_Nok_UserType(8),
  /**
   * <code>User_Profile_Nok_ServiceType = 9;</code>
   */
  User_Profile_Nok_ServiceType(9),
  /**
   * <code>User_Profile_Nok_DB = 10;</code>
   */
  User_Profile_Nok_DB(10),
  /**
   * <code>User_Profile_Nok_Reason1 = 11;</code>
   */
  User_Profile_Nok_Reason1(11),
  /**
   * <code>User_Profile_Nok_Reason2 = 12;</code>
   */
  User_Profile_Nok_Reason2(12),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>User_ProfileUSER_PROFILE_UNKNOWN = 0;</code>
   */
  public static final int User_ProfileUSER_PROFILE_UNKNOWN_VALUE = 0;
  /**
   * <code>User_Profile_Ok = 1;</code>
   */
  public static final int User_Profile_Ok_VALUE = 1;
  /**
   * <code>User_Profile_Nok_OrgName = 2;</code>
   */
  public static final int User_Profile_Nok_OrgName_VALUE = 2;
  /**
   * <code>User_Profile_Nok_UserName = 3;</code>
   */
  public static final int User_Profile_Nok_UserName_VALUE = 3;
  /**
   * <code>User_Profile_Nok_UserPwd = 4;</code>
   */
  public static final int User_Profile_Nok_UserPwd_VALUE = 4;
  /**
   * <code>User_Profile_Nok_ServiceDuration = 5;</code>
   */
  public static final int User_Profile_Nok_ServiceDuration_VALUE = 5;
  /**
   * <code>User_Profile_Nok_Age = 6;</code>
   */
  public static final int User_Profile_Nok_Age_VALUE = 6;
  /**
   * <code>User_Profile_Nok_Sex = 7;</code>
   */
  public static final int User_Profile_Nok_Sex_VALUE = 7;
  /**
   * <code>User_Profile_Nok_UserType = 8;</code>
   */
  public static final int User_Profile_Nok_UserType_VALUE = 8;
  /**
   * <code>User_Profile_Nok_ServiceType = 9;</code>
   */
  public static final int User_Profile_Nok_ServiceType_VALUE = 9;
  /**
   * <code>User_Profile_Nok_DB = 10;</code>
   */
  public static final int User_Profile_Nok_DB_VALUE = 10;
  /**
   * <code>User_Profile_Nok_Reason1 = 11;</code>
   */
  public static final int User_Profile_Nok_Reason1_VALUE = 11;
  /**
   * <code>User_Profile_Nok_Reason2 = 12;</code>
   */
  public static final int User_Profile_Nok_Reason2_VALUE = 12;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static UserProfileResult_e valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static UserProfileResult_e forNumber(int value) {
    switch (value) {
      case 0: return User_ProfileUSER_PROFILE_UNKNOWN;
      case 1: return User_Profile_Ok;
      case 2: return User_Profile_Nok_OrgName;
      case 3: return User_Profile_Nok_UserName;
      case 4: return User_Profile_Nok_UserPwd;
      case 5: return User_Profile_Nok_ServiceDuration;
      case 6: return User_Profile_Nok_Age;
      case 7: return User_Profile_Nok_Sex;
      case 8: return User_Profile_Nok_UserType;
      case 9: return User_Profile_Nok_ServiceType;
      case 10: return User_Profile_Nok_DB;
      case 11: return User_Profile_Nok_Reason1;
      case 12: return User_Profile_Nok_Reason2;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<UserProfileResult_e>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      UserProfileResult_e> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<UserProfileResult_e>() {
          public UserProfileResult_e findValueByNumber(int number) {
            return UserProfileResult_e.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalStateException(
          "Can't get the descriptor of an unrecognized enum value.");
    }
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return com.erksystem.protobuf.api.ErkApiMsgOuterClass.getDescriptor().getEnumTypes().get(7);
  }

  private static final UserProfileResult_e[] VALUES = values();

  public static UserProfileResult_e valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private UserProfileResult_e(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:UserProfileResult_e)
}

