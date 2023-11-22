// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ErkProvMsg.proto

// Protobuf Java Version: 3.25.0
package com.erksystem.protobuf.prov;

/**
 * Protobuf type {@code DelUserInfoRP_m}
 */
public final class DelUserInfoRP_m extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:DelUserInfoRP_m)
    DelUserInfoRP_mOrBuilder {
private static final long serialVersionUID = 0L;
  // Use DelUserInfoRP_m.newBuilder() to construct.
  private DelUserInfoRP_m(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DelUserInfoRP_m() {
    orgName_ = "";
    userName_ = "";
    resultType_ = 0;
    return_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new DelUserInfoRP_m();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.erksystem.protobuf.prov.ErkProvMsg.internal_static_DelUserInfoRP_m_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.erksystem.protobuf.prov.ErkProvMsg.internal_static_DelUserInfoRP_m_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.erksystem.protobuf.prov.DelUserInfoRP_m.class, com.erksystem.protobuf.prov.DelUserInfoRP_m.Builder.class);
  }

  public static final int ORGNAME_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object orgName_ = "";
  /**
   * <code>string OrgName = 1;</code>
   * @return The orgName.
   */
  @java.lang.Override
  public java.lang.String getOrgName() {
    java.lang.Object ref = orgName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      orgName_ = s;
      return s;
    }
  }
  /**
   * <code>string OrgName = 1;</code>
   * @return The bytes for orgName.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getOrgNameBytes() {
    java.lang.Object ref = orgName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      orgName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int USERNAME_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile java.lang.Object userName_ = "";
  /**
   * <code>string UserName = 2;</code>
   * @return The userName.
   */
  @java.lang.Override
  public java.lang.String getUserName() {
    java.lang.Object ref = userName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      userName_ = s;
      return s;
    }
  }
  /**
   * <code>string UserName = 2;</code>
   * @return The bytes for userName.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getUserNameBytes() {
    java.lang.Object ref = userName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      userName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int RESULTTYPE_FIELD_NUMBER = 3;
  private int resultType_ = 0;
  /**
   * <code>.UserProfileResult_e ResultType = 3;</code>
   * @return The enum numeric value on the wire for resultType.
   */
  @java.lang.Override public int getResultTypeValue() {
    return resultType_;
  }
  /**
   * <code>.UserProfileResult_e ResultType = 3;</code>
   * @return The resultType.
   */
  @java.lang.Override public com.erksystem.protobuf.prov.UserProfileResult_e getResultType() {
    com.erksystem.protobuf.prov.UserProfileResult_e result = com.erksystem.protobuf.prov.UserProfileResult_e.forNumber(resultType_);
    return result == null ? com.erksystem.protobuf.prov.UserProfileResult_e.UNRECOGNIZED : result;
  }

  public static final int RETURN_FIELD_NUMBER = 4;
  @SuppressWarnings("serial")
  private volatile java.lang.Object return_ = "";
  /**
   * <code>string Return = 4;</code>
   * @return The return.
   */
  @java.lang.Override
  public java.lang.String getReturn() {
    java.lang.Object ref = return_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      return_ = s;
      return s;
    }
  }
  /**
   * <code>string Return = 4;</code>
   * @return The bytes for return.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getReturnBytes() {
    java.lang.Object ref = return_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      return_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(orgName_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, orgName_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(userName_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, userName_);
    }
    if (resultType_ != com.erksystem.protobuf.prov.UserProfileResult_e.UserProfileResult_unknown.getNumber()) {
      output.writeEnum(3, resultType_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(return_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, return_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(orgName_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, orgName_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(userName_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, userName_);
    }
    if (resultType_ != com.erksystem.protobuf.prov.UserProfileResult_e.UserProfileResult_unknown.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(3, resultType_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(return_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, return_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.erksystem.protobuf.prov.DelUserInfoRP_m)) {
      return super.equals(obj);
    }
    com.erksystem.protobuf.prov.DelUserInfoRP_m other = (com.erksystem.protobuf.prov.DelUserInfoRP_m) obj;

    if (!getOrgName()
        .equals(other.getOrgName())) return false;
    if (!getUserName()
        .equals(other.getUserName())) return false;
    if (resultType_ != other.resultType_) return false;
    if (!getReturn()
        .equals(other.getReturn())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ORGNAME_FIELD_NUMBER;
    hash = (53 * hash) + getOrgName().hashCode();
    hash = (37 * hash) + USERNAME_FIELD_NUMBER;
    hash = (53 * hash) + getUserName().hashCode();
    hash = (37 * hash) + RESULTTYPE_FIELD_NUMBER;
    hash = (53 * hash) + resultType_;
    hash = (37 * hash) + RETURN_FIELD_NUMBER;
    hash = (53 * hash) + getReturn().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.erksystem.protobuf.prov.DelUserInfoRP_m parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.erksystem.protobuf.prov.DelUserInfoRP_m parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.erksystem.protobuf.prov.DelUserInfoRP_m parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.erksystem.protobuf.prov.DelUserInfoRP_m parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.erksystem.protobuf.prov.DelUserInfoRP_m parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.erksystem.protobuf.prov.DelUserInfoRP_m parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.erksystem.protobuf.prov.DelUserInfoRP_m parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.erksystem.protobuf.prov.DelUserInfoRP_m parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.erksystem.protobuf.prov.DelUserInfoRP_m parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.erksystem.protobuf.prov.DelUserInfoRP_m parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.erksystem.protobuf.prov.DelUserInfoRP_m parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.erksystem.protobuf.prov.DelUserInfoRP_m parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.erksystem.protobuf.prov.DelUserInfoRP_m prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code DelUserInfoRP_m}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:DelUserInfoRP_m)
      com.erksystem.protobuf.prov.DelUserInfoRP_mOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.erksystem.protobuf.prov.ErkProvMsg.internal_static_DelUserInfoRP_m_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.erksystem.protobuf.prov.ErkProvMsg.internal_static_DelUserInfoRP_m_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.erksystem.protobuf.prov.DelUserInfoRP_m.class, com.erksystem.protobuf.prov.DelUserInfoRP_m.Builder.class);
    }

    // Construct using com.erksystem.protobuf.prov.DelUserInfoRP_m.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      orgName_ = "";
      userName_ = "";
      resultType_ = 0;
      return_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.erksystem.protobuf.prov.ErkProvMsg.internal_static_DelUserInfoRP_m_descriptor;
    }

    @java.lang.Override
    public com.erksystem.protobuf.prov.DelUserInfoRP_m getDefaultInstanceForType() {
      return com.erksystem.protobuf.prov.DelUserInfoRP_m.getDefaultInstance();
    }

    @java.lang.Override
    public com.erksystem.protobuf.prov.DelUserInfoRP_m build() {
      com.erksystem.protobuf.prov.DelUserInfoRP_m result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.erksystem.protobuf.prov.DelUserInfoRP_m buildPartial() {
      com.erksystem.protobuf.prov.DelUserInfoRP_m result = new com.erksystem.protobuf.prov.DelUserInfoRP_m(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.erksystem.protobuf.prov.DelUserInfoRP_m result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.orgName_ = orgName_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.userName_ = userName_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.resultType_ = resultType_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.return_ = return_;
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.erksystem.protobuf.prov.DelUserInfoRP_m) {
        return mergeFrom((com.erksystem.protobuf.prov.DelUserInfoRP_m)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.erksystem.protobuf.prov.DelUserInfoRP_m other) {
      if (other == com.erksystem.protobuf.prov.DelUserInfoRP_m.getDefaultInstance()) return this;
      if (!other.getOrgName().isEmpty()) {
        orgName_ = other.orgName_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      if (!other.getUserName().isEmpty()) {
        userName_ = other.userName_;
        bitField0_ |= 0x00000002;
        onChanged();
      }
      if (other.resultType_ != 0) {
        setResultTypeValue(other.getResultTypeValue());
      }
      if (!other.getReturn().isEmpty()) {
        return_ = other.return_;
        bitField0_ |= 0x00000008;
        onChanged();
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              orgName_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              userName_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 24: {
              resultType_ = input.readEnum();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            case 34: {
              return_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000008;
              break;
            } // case 34
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private java.lang.Object orgName_ = "";
    /**
     * <code>string OrgName = 1;</code>
     * @return The orgName.
     */
    public java.lang.String getOrgName() {
      java.lang.Object ref = orgName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        orgName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string OrgName = 1;</code>
     * @return The bytes for orgName.
     */
    public com.google.protobuf.ByteString
        getOrgNameBytes() {
      java.lang.Object ref = orgName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        orgName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string OrgName = 1;</code>
     * @param value The orgName to set.
     * @return This builder for chaining.
     */
    public Builder setOrgName(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      orgName_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string OrgName = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearOrgName() {
      orgName_ = getDefaultInstance().getOrgName();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string OrgName = 1;</code>
     * @param value The bytes for orgName to set.
     * @return This builder for chaining.
     */
    public Builder setOrgNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      orgName_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    private java.lang.Object userName_ = "";
    /**
     * <code>string UserName = 2;</code>
     * @return The userName.
     */
    public java.lang.String getUserName() {
      java.lang.Object ref = userName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        userName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string UserName = 2;</code>
     * @return The bytes for userName.
     */
    public com.google.protobuf.ByteString
        getUserNameBytes() {
      java.lang.Object ref = userName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        userName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string UserName = 2;</code>
     * @param value The userName to set.
     * @return This builder for chaining.
     */
    public Builder setUserName(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      userName_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>string UserName = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearUserName() {
      userName_ = getDefaultInstance().getUserName();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <code>string UserName = 2;</code>
     * @param value The bytes for userName to set.
     * @return This builder for chaining.
     */
    public Builder setUserNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      userName_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }

    private int resultType_ = 0;
    /**
     * <code>.UserProfileResult_e ResultType = 3;</code>
     * @return The enum numeric value on the wire for resultType.
     */
    @java.lang.Override public int getResultTypeValue() {
      return resultType_;
    }
    /**
     * <code>.UserProfileResult_e ResultType = 3;</code>
     * @param value The enum numeric value on the wire for resultType to set.
     * @return This builder for chaining.
     */
    public Builder setResultTypeValue(int value) {
      resultType_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>.UserProfileResult_e ResultType = 3;</code>
     * @return The resultType.
     */
    @java.lang.Override
    public com.erksystem.protobuf.prov.UserProfileResult_e getResultType() {
      com.erksystem.protobuf.prov.UserProfileResult_e result = com.erksystem.protobuf.prov.UserProfileResult_e.forNumber(resultType_);
      return result == null ? com.erksystem.protobuf.prov.UserProfileResult_e.UNRECOGNIZED : result;
    }
    /**
     * <code>.UserProfileResult_e ResultType = 3;</code>
     * @param value The resultType to set.
     * @return This builder for chaining.
     */
    public Builder setResultType(com.erksystem.protobuf.prov.UserProfileResult_e value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000004;
      resultType_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.UserProfileResult_e ResultType = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearResultType() {
      bitField0_ = (bitField0_ & ~0x00000004);
      resultType_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object return_ = "";
    /**
     * <code>string Return = 4;</code>
     * @return The return.
     */
    public java.lang.String getReturn() {
      java.lang.Object ref = return_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        return_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string Return = 4;</code>
     * @return The bytes for return.
     */
    public com.google.protobuf.ByteString
        getReturnBytes() {
      java.lang.Object ref = return_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        return_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string Return = 4;</code>
     * @param value The return to set.
     * @return This builder for chaining.
     */
    public Builder setReturn(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      return_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>string Return = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearReturn() {
      return_ = getDefaultInstance().getReturn();
      bitField0_ = (bitField0_ & ~0x00000008);
      onChanged();
      return this;
    }
    /**
     * <code>string Return = 4;</code>
     * @param value The bytes for return to set.
     * @return This builder for chaining.
     */
    public Builder setReturnBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      return_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:DelUserInfoRP_m)
  }

  // @@protoc_insertion_point(class_scope:DelUserInfoRP_m)
  private static final com.erksystem.protobuf.prov.DelUserInfoRP_m DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.erksystem.protobuf.prov.DelUserInfoRP_m();
  }

  public static com.erksystem.protobuf.prov.DelUserInfoRP_m getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DelUserInfoRP_m>
      PARSER = new com.google.protobuf.AbstractParser<DelUserInfoRP_m>() {
    @java.lang.Override
    public DelUserInfoRP_m parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<DelUserInfoRP_m> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DelUserInfoRP_m> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.erksystem.protobuf.prov.DelUserInfoRP_m getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
