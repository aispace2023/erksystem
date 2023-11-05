// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ErkProvMsg.proto

package com.erksystem.protobuf.prov;

/**
 * Protobuf enum {@code UserProfileResult_e}
 */
public enum UserProfileResult_e
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>USER_PROFILE_UNKNOWN = 0;</code>
   */
  USER_PROFILE_UNKNOWN(0),
  /**
   * <code>USER_PROFILE_OK = 1;</code>
   */
  USER_PROFILE_OK(1),
  /**
   * <code>USER_PROFILE_NOK_ORGNAME = 2;</code>
   */
  USER_PROFILE_NOK_ORGNAME(2),
  /**
   * <code>USER_PROFILE_NOK_USERNAME = 3;</code>
   */
  USER_PROFILE_NOK_USERNAME(3),
  /**
   * <code>USER_PROFILE_NOK_USERPWD = 4;</code>
   */
  USER_PROFILE_NOK_USERPWD(4),
  /**
   * <code>USER_PROFILE_NOK_SERVICEDURATION = 5;</code>
   */
  USER_PROFILE_NOK_SERVICEDURATION(5),
  /**
   * <code>USER_PROFILE_NOK_AGE = 6;</code>
   */
  USER_PROFILE_NOK_AGE(6),
  /**
   * <code>USER_PROFILE_NOK_SEX = 7;</code>
   */
  USER_PROFILE_NOK_SEX(7),
  /**
   * <code>USER_PROFILE_NOK_USERTYPE = 8;</code>
   */
  USER_PROFILE_NOK_USERTYPE(8),
  /**
   * <code>USER_PROFILE_NOK_SERVICETYPE = 9;</code>
   */
  USER_PROFILE_NOK_SERVICETYPE(9),
  /**
   * <code>USER_PROFILE_NOK_DB = 10;</code>
   */
  USER_PROFILE_NOK_DB(10),
  /**
   * <code>USER_PROFILE_NOK_REASON1 = 11;</code>
   */
  USER_PROFILE_NOK_REASON1(11),
  /**
   * <code>USER_PROFILE_NOK_REASON2 = 12;</code>
   */
  USER_PROFILE_NOK_REASON2(12),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>USER_PROFILE_UNKNOWN = 0;</code>
   */
  public static final int USER_PROFILE_UNKNOWN_VALUE = 0;
  /**
   * <code>USER_PROFILE_OK = 1;</code>
   */
  public static final int USER_PROFILE_OK_VALUE = 1;
  /**
   * <code>USER_PROFILE_NOK_ORGNAME = 2;</code>
   */
  public static final int USER_PROFILE_NOK_ORGNAME_VALUE = 2;
  /**
   * <code>USER_PROFILE_NOK_USERNAME = 3;</code>
   */
  public static final int USER_PROFILE_NOK_USERNAME_VALUE = 3;
  /**
   * <code>USER_PROFILE_NOK_USERPWD = 4;</code>
   */
  public static final int USER_PROFILE_NOK_USERPWD_VALUE = 4;
  /**
   * <code>USER_PROFILE_NOK_SERVICEDURATION = 5;</code>
   */
  public static final int USER_PROFILE_NOK_SERVICEDURATION_VALUE = 5;
  /**
   * <code>USER_PROFILE_NOK_AGE = 6;</code>
   */
  public static final int USER_PROFILE_NOK_AGE_VALUE = 6;
  /**
   * <code>USER_PROFILE_NOK_SEX = 7;</code>
   */
  public static final int USER_PROFILE_NOK_SEX_VALUE = 7;
  /**
   * <code>USER_PROFILE_NOK_USERTYPE = 8;</code>
   */
  public static final int USER_PROFILE_NOK_USERTYPE_VALUE = 8;
  /**
   * <code>USER_PROFILE_NOK_SERVICETYPE = 9;</code>
   */
  public static final int USER_PROFILE_NOK_SERVICETYPE_VALUE = 9;
  /**
   * <code>USER_PROFILE_NOK_DB = 10;</code>
   */
  public static final int USER_PROFILE_NOK_DB_VALUE = 10;
  /**
   * <code>USER_PROFILE_NOK_REASON1 = 11;</code>
   */
  public static final int USER_PROFILE_NOK_REASON1_VALUE = 11;
  /**
   * <code>USER_PROFILE_NOK_REASON2 = 12;</code>
   */
  public static final int USER_PROFILE_NOK_REASON2_VALUE = 12;


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
      case 0: return USER_PROFILE_UNKNOWN;
      case 1: return USER_PROFILE_OK;
      case 2: return USER_PROFILE_NOK_ORGNAME;
      case 3: return USER_PROFILE_NOK_USERNAME;
      case 4: return USER_PROFILE_NOK_USERPWD;
      case 5: return USER_PROFILE_NOK_SERVICEDURATION;
      case 6: return USER_PROFILE_NOK_AGE;
      case 7: return USER_PROFILE_NOK_SEX;
      case 8: return USER_PROFILE_NOK_USERTYPE;
      case 9: return USER_PROFILE_NOK_SERVICETYPE;
      case 10: return USER_PROFILE_NOK_DB;
      case 11: return USER_PROFILE_NOK_REASON1;
      case 12: return USER_PROFILE_NOK_REASON2;
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
    return com.erksystem.protobuf.prov.ErkProvMsgOuterClass.getDescriptor().getEnumTypes().get(7);
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

