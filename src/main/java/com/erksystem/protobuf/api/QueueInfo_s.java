// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ErkApiMsg.proto

// Protobuf Java Version: 3.25.0
package com.erksystem.protobuf.api;

/**
 * Protobuf type {@code QueueInfo_s}
 */
public final class QueueInfo_s extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:QueueInfo_s)
    QueueInfo_sOrBuilder {
private static final long serialVersionUID = 0L;
  // Use QueueInfo_s.newBuilder() to construct.
  private QueueInfo_s(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueueInfo_s() {
    toQueueName_ = "";
    fromQueueName_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new QueueInfo_s();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.erksystem.protobuf.api.ErkApiMsgOuterClass.internal_static_QueueInfo_s_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.erksystem.protobuf.api.ErkApiMsgOuterClass.internal_static_QueueInfo_s_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.erksystem.protobuf.api.QueueInfo_s.class, com.erksystem.protobuf.api.QueueInfo_s.Builder.class);
  }

  public static final int TOQUEUENAME_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object toQueueName_ = "";
  /**
   * <code>string ToQueueName = 1;</code>
   * @return The toQueueName.
   */
  @java.lang.Override
  public java.lang.String getToQueueName() {
    java.lang.Object ref = toQueueName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      toQueueName_ = s;
      return s;
    }
  }
  /**
   * <code>string ToQueueName = 1;</code>
   * @return The bytes for toQueueName.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getToQueueNameBytes() {
    java.lang.Object ref = toQueueName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      toQueueName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int FROMQUEUENAME_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile java.lang.Object fromQueueName_ = "";
  /**
   * <code>string FromQueueName = 2;</code>
   * @return The fromQueueName.
   */
  @java.lang.Override
  public java.lang.String getFromQueueName() {
    java.lang.Object ref = fromQueueName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      fromQueueName_ = s;
      return s;
    }
  }
  /**
   * <code>string FromQueueName = 2;</code>
   * @return The bytes for fromQueueName.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getFromQueueNameBytes() {
    java.lang.Object ref = fromQueueName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      fromQueueName_ = b;
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
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(toQueueName_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, toQueueName_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(fromQueueName_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, fromQueueName_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(toQueueName_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, toQueueName_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(fromQueueName_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, fromQueueName_);
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
    if (!(obj instanceof com.erksystem.protobuf.api.QueueInfo_s)) {
      return super.equals(obj);
    }
    com.erksystem.protobuf.api.QueueInfo_s other = (com.erksystem.protobuf.api.QueueInfo_s) obj;

    if (!getToQueueName()
        .equals(other.getToQueueName())) return false;
    if (!getFromQueueName()
        .equals(other.getFromQueueName())) return false;
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
    hash = (37 * hash) + TOQUEUENAME_FIELD_NUMBER;
    hash = (53 * hash) + getToQueueName().hashCode();
    hash = (37 * hash) + FROMQUEUENAME_FIELD_NUMBER;
    hash = (53 * hash) + getFromQueueName().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.erksystem.protobuf.api.QueueInfo_s parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.erksystem.protobuf.api.QueueInfo_s parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.erksystem.protobuf.api.QueueInfo_s parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.erksystem.protobuf.api.QueueInfo_s parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.erksystem.protobuf.api.QueueInfo_s parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.erksystem.protobuf.api.QueueInfo_s parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.erksystem.protobuf.api.QueueInfo_s parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.erksystem.protobuf.api.QueueInfo_s parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.erksystem.protobuf.api.QueueInfo_s parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.erksystem.protobuf.api.QueueInfo_s parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.erksystem.protobuf.api.QueueInfo_s parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.erksystem.protobuf.api.QueueInfo_s parseFrom(
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
  public static Builder newBuilder(com.erksystem.protobuf.api.QueueInfo_s prototype) {
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
   * Protobuf type {@code QueueInfo_s}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:QueueInfo_s)
      com.erksystem.protobuf.api.QueueInfo_sOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.erksystem.protobuf.api.ErkApiMsgOuterClass.internal_static_QueueInfo_s_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.erksystem.protobuf.api.ErkApiMsgOuterClass.internal_static_QueueInfo_s_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.erksystem.protobuf.api.QueueInfo_s.class, com.erksystem.protobuf.api.QueueInfo_s.Builder.class);
    }

    // Construct using com.erksystem.protobuf.api.QueueInfo_s.newBuilder()
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
      toQueueName_ = "";
      fromQueueName_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.erksystem.protobuf.api.ErkApiMsgOuterClass.internal_static_QueueInfo_s_descriptor;
    }

    @java.lang.Override
    public com.erksystem.protobuf.api.QueueInfo_s getDefaultInstanceForType() {
      return com.erksystem.protobuf.api.QueueInfo_s.getDefaultInstance();
    }

    @java.lang.Override
    public com.erksystem.protobuf.api.QueueInfo_s build() {
      com.erksystem.protobuf.api.QueueInfo_s result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.erksystem.protobuf.api.QueueInfo_s buildPartial() {
      com.erksystem.protobuf.api.QueueInfo_s result = new com.erksystem.protobuf.api.QueueInfo_s(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.erksystem.protobuf.api.QueueInfo_s result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.toQueueName_ = toQueueName_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.fromQueueName_ = fromQueueName_;
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
      if (other instanceof com.erksystem.protobuf.api.QueueInfo_s) {
        return mergeFrom((com.erksystem.protobuf.api.QueueInfo_s)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.erksystem.protobuf.api.QueueInfo_s other) {
      if (other == com.erksystem.protobuf.api.QueueInfo_s.getDefaultInstance()) return this;
      if (!other.getToQueueName().isEmpty()) {
        toQueueName_ = other.toQueueName_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      if (!other.getFromQueueName().isEmpty()) {
        fromQueueName_ = other.fromQueueName_;
        bitField0_ |= 0x00000002;
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
              toQueueName_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              fromQueueName_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
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

    private java.lang.Object toQueueName_ = "";
    /**
     * <code>string ToQueueName = 1;</code>
     * @return The toQueueName.
     */
    public java.lang.String getToQueueName() {
      java.lang.Object ref = toQueueName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        toQueueName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string ToQueueName = 1;</code>
     * @return The bytes for toQueueName.
     */
    public com.google.protobuf.ByteString
        getToQueueNameBytes() {
      java.lang.Object ref = toQueueName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        toQueueName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string ToQueueName = 1;</code>
     * @param value The toQueueName to set.
     * @return This builder for chaining.
     */
    public Builder setToQueueName(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      toQueueName_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string ToQueueName = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearToQueueName() {
      toQueueName_ = getDefaultInstance().getToQueueName();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string ToQueueName = 1;</code>
     * @param value The bytes for toQueueName to set.
     * @return This builder for chaining.
     */
    public Builder setToQueueNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      toQueueName_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    private java.lang.Object fromQueueName_ = "";
    /**
     * <code>string FromQueueName = 2;</code>
     * @return The fromQueueName.
     */
    public java.lang.String getFromQueueName() {
      java.lang.Object ref = fromQueueName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        fromQueueName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string FromQueueName = 2;</code>
     * @return The bytes for fromQueueName.
     */
    public com.google.protobuf.ByteString
        getFromQueueNameBytes() {
      java.lang.Object ref = fromQueueName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        fromQueueName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string FromQueueName = 2;</code>
     * @param value The fromQueueName to set.
     * @return This builder for chaining.
     */
    public Builder setFromQueueName(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      fromQueueName_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>string FromQueueName = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearFromQueueName() {
      fromQueueName_ = getDefaultInstance().getFromQueueName();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <code>string FromQueueName = 2;</code>
     * @param value The bytes for fromQueueName to set.
     * @return This builder for chaining.
     */
    public Builder setFromQueueNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      fromQueueName_ = value;
      bitField0_ |= 0x00000002;
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


    // @@protoc_insertion_point(builder_scope:QueueInfo_s)
  }

  // @@protoc_insertion_point(class_scope:QueueInfo_s)
  private static final com.erksystem.protobuf.api.QueueInfo_s DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.erksystem.protobuf.api.QueueInfo_s();
  }

  public static com.erksystem.protobuf.api.QueueInfo_s getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QueueInfo_s>
      PARSER = new com.google.protobuf.AbstractParser<QueueInfo_s>() {
    @java.lang.Override
    public QueueInfo_s parsePartialFrom(
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

  public static com.google.protobuf.Parser<QueueInfo_s> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QueueInfo_s> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.erksystem.protobuf.api.QueueInfo_s getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
