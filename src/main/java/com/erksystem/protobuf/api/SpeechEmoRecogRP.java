// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ErkApiMsg.proto

package com.erksystem.protobuf.api;

/**
 * Protobuf type {@code SpeechEmoRecogRP}
 */
public final class SpeechEmoRecogRP extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:SpeechEmoRecogRP)
    SpeechEmoRecogRPOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SpeechEmoRecogRP.newBuilder() to construct.
  private SpeechEmoRecogRP(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SpeechEmoRecogRP() {
    returnCode_ = 0;
    emotion_ = 0;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new SpeechEmoRecogRP();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.erksystem.protobuf.api.ErkApiMsgOuterClass.internal_static_SpeechEmoRecogRP_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.erksystem.protobuf.api.ErkApiMsgOuterClass.internal_static_SpeechEmoRecogRP_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.erksystem.protobuf.api.SpeechEmoRecogRP.class, com.erksystem.protobuf.api.SpeechEmoRecogRP.Builder.class);
  }

  public static final int ERKMSGDATAHEAD_FIELD_NUMBER = 1;
  private com.erksystem.protobuf.api.ErkMsgDataHeader erkMsgDataHead_;
  /**
   * <code>.ErkMsgDataHeader ErkMsgDataHead = 1;</code>
   * @return Whether the erkMsgDataHead field is set.
   */
  @java.lang.Override
  public boolean hasErkMsgDataHead() {
    return erkMsgDataHead_ != null;
  }
  /**
   * <code>.ErkMsgDataHeader ErkMsgDataHead = 1;</code>
   * @return The erkMsgDataHead.
   */
  @java.lang.Override
  public com.erksystem.protobuf.api.ErkMsgDataHeader getErkMsgDataHead() {
    return erkMsgDataHead_ == null ? com.erksystem.protobuf.api.ErkMsgDataHeader.getDefaultInstance() : erkMsgDataHead_;
  }
  /**
   * <code>.ErkMsgDataHeader ErkMsgDataHead = 1;</code>
   */
  @java.lang.Override
  public com.erksystem.protobuf.api.ErkMsgDataHeaderOrBuilder getErkMsgDataHeadOrBuilder() {
    return erkMsgDataHead_ == null ? com.erksystem.protobuf.api.ErkMsgDataHeader.getDefaultInstance() : erkMsgDataHead_;
  }

  public static final int RETURNCODE_FIELD_NUMBER = 2;
  private int returnCode_ = 0;
  /**
   * <code>.ReturnCode_e ReturnCode = 2;</code>
   * @return The enum numeric value on the wire for returnCode.
   */
  @java.lang.Override public int getReturnCodeValue() {
    return returnCode_;
  }
  /**
   * <code>.ReturnCode_e ReturnCode = 2;</code>
   * @return The returnCode.
   */
  @java.lang.Override public com.erksystem.protobuf.api.ReturnCode_e getReturnCode() {
    com.erksystem.protobuf.api.ReturnCode_e result = com.erksystem.protobuf.api.ReturnCode_e.forNumber(returnCode_);
    return result == null ? com.erksystem.protobuf.api.ReturnCode_e.UNRECOGNIZED : result;
  }

  public static final int EMORECOGTIME_FIELD_NUMBER = 3;
  private long emoRecogTime_ = 0L;
  /**
   * <code>int64 EmoRecogTime = 3;</code>
   * @return The emoRecogTime.
   */
  @java.lang.Override
  public long getEmoRecogTime() {
    return emoRecogTime_;
  }

  public static final int EMOTION_FIELD_NUMBER = 4;
  private int emotion_ = 0;
  /**
   * <code>.EmotionType_e Emotion = 4;</code>
   * @return The enum numeric value on the wire for emotion.
   */
  @java.lang.Override public int getEmotionValue() {
    return emotion_;
  }
  /**
   * <code>.EmotionType_e Emotion = 4;</code>
   * @return The emotion.
   */
  @java.lang.Override public com.erksystem.protobuf.api.EmotionType_e getEmotion() {
    com.erksystem.protobuf.api.EmotionType_e result = com.erksystem.protobuf.api.EmotionType_e.forNumber(emotion_);
    return result == null ? com.erksystem.protobuf.api.EmotionType_e.UNRECOGNIZED : result;
  }

  public static final int ACCURACY_FIELD_NUMBER = 5;
  private float accuracy_ = 0F;
  /**
   * <code>float Accuracy = 5;</code>
   * @return The accuracy.
   */
  @java.lang.Override
  public float getAccuracy() {
    return accuracy_;
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
    if (erkMsgDataHead_ != null) {
      output.writeMessage(1, getErkMsgDataHead());
    }
    if (returnCode_ != com.erksystem.protobuf.api.ReturnCode_e.unknown.getNumber()) {
      output.writeEnum(2, returnCode_);
    }
    if (emoRecogTime_ != 0L) {
      output.writeInt64(3, emoRecogTime_);
    }
    if (emotion_ != com.erksystem.protobuf.api.EmotionType_e.EMOTION_UNKNOWN.getNumber()) {
      output.writeEnum(4, emotion_);
    }
    if (java.lang.Float.floatToRawIntBits(accuracy_) != 0) {
      output.writeFloat(5, accuracy_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (erkMsgDataHead_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getErkMsgDataHead());
    }
    if (returnCode_ != com.erksystem.protobuf.api.ReturnCode_e.unknown.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, returnCode_);
    }
    if (emoRecogTime_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, emoRecogTime_);
    }
    if (emotion_ != com.erksystem.protobuf.api.EmotionType_e.EMOTION_UNKNOWN.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(4, emotion_);
    }
    if (java.lang.Float.floatToRawIntBits(accuracy_) != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeFloatSize(5, accuracy_);
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
    if (!(obj instanceof com.erksystem.protobuf.api.SpeechEmoRecogRP)) {
      return super.equals(obj);
    }
    com.erksystem.protobuf.api.SpeechEmoRecogRP other = (com.erksystem.protobuf.api.SpeechEmoRecogRP) obj;

    if (hasErkMsgDataHead() != other.hasErkMsgDataHead()) return false;
    if (hasErkMsgDataHead()) {
      if (!getErkMsgDataHead()
          .equals(other.getErkMsgDataHead())) return false;
    }
    if (returnCode_ != other.returnCode_) return false;
    if (getEmoRecogTime()
        != other.getEmoRecogTime()) return false;
    if (emotion_ != other.emotion_) return false;
    if (java.lang.Float.floatToIntBits(getAccuracy())
        != java.lang.Float.floatToIntBits(
            other.getAccuracy())) return false;
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
    if (hasErkMsgDataHead()) {
      hash = (37 * hash) + ERKMSGDATAHEAD_FIELD_NUMBER;
      hash = (53 * hash) + getErkMsgDataHead().hashCode();
    }
    hash = (37 * hash) + RETURNCODE_FIELD_NUMBER;
    hash = (53 * hash) + returnCode_;
    hash = (37 * hash) + EMORECOGTIME_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getEmoRecogTime());
    hash = (37 * hash) + EMOTION_FIELD_NUMBER;
    hash = (53 * hash) + emotion_;
    hash = (37 * hash) + ACCURACY_FIELD_NUMBER;
    hash = (53 * hash) + java.lang.Float.floatToIntBits(
        getAccuracy());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.erksystem.protobuf.api.SpeechEmoRecogRP parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.erksystem.protobuf.api.SpeechEmoRecogRP parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.erksystem.protobuf.api.SpeechEmoRecogRP parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.erksystem.protobuf.api.SpeechEmoRecogRP parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.erksystem.protobuf.api.SpeechEmoRecogRP parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.erksystem.protobuf.api.SpeechEmoRecogRP parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.erksystem.protobuf.api.SpeechEmoRecogRP parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.erksystem.protobuf.api.SpeechEmoRecogRP parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.erksystem.protobuf.api.SpeechEmoRecogRP parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.erksystem.protobuf.api.SpeechEmoRecogRP parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.erksystem.protobuf.api.SpeechEmoRecogRP parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.erksystem.protobuf.api.SpeechEmoRecogRP parseFrom(
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
  public static Builder newBuilder(com.erksystem.protobuf.api.SpeechEmoRecogRP prototype) {
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
   * Protobuf type {@code SpeechEmoRecogRP}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:SpeechEmoRecogRP)
      com.erksystem.protobuf.api.SpeechEmoRecogRPOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.erksystem.protobuf.api.ErkApiMsgOuterClass.internal_static_SpeechEmoRecogRP_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.erksystem.protobuf.api.ErkApiMsgOuterClass.internal_static_SpeechEmoRecogRP_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.erksystem.protobuf.api.SpeechEmoRecogRP.class, com.erksystem.protobuf.api.SpeechEmoRecogRP.Builder.class);
    }

    // Construct using com.erksystem.protobuf.api.SpeechEmoRecogRP.newBuilder()
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
      erkMsgDataHead_ = null;
      if (erkMsgDataHeadBuilder_ != null) {
        erkMsgDataHeadBuilder_.dispose();
        erkMsgDataHeadBuilder_ = null;
      }
      returnCode_ = 0;
      emoRecogTime_ = 0L;
      emotion_ = 0;
      accuracy_ = 0F;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.erksystem.protobuf.api.ErkApiMsgOuterClass.internal_static_SpeechEmoRecogRP_descriptor;
    }

    @java.lang.Override
    public com.erksystem.protobuf.api.SpeechEmoRecogRP getDefaultInstanceForType() {
      return com.erksystem.protobuf.api.SpeechEmoRecogRP.getDefaultInstance();
    }

    @java.lang.Override
    public com.erksystem.protobuf.api.SpeechEmoRecogRP build() {
      com.erksystem.protobuf.api.SpeechEmoRecogRP result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.erksystem.protobuf.api.SpeechEmoRecogRP buildPartial() {
      com.erksystem.protobuf.api.SpeechEmoRecogRP result = new com.erksystem.protobuf.api.SpeechEmoRecogRP(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.erksystem.protobuf.api.SpeechEmoRecogRP result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.erkMsgDataHead_ = erkMsgDataHeadBuilder_ == null
            ? erkMsgDataHead_
            : erkMsgDataHeadBuilder_.build();
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.returnCode_ = returnCode_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.emoRecogTime_ = emoRecogTime_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.emotion_ = emotion_;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        result.accuracy_ = accuracy_;
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
      if (other instanceof com.erksystem.protobuf.api.SpeechEmoRecogRP) {
        return mergeFrom((com.erksystem.protobuf.api.SpeechEmoRecogRP)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.erksystem.protobuf.api.SpeechEmoRecogRP other) {
      if (other == com.erksystem.protobuf.api.SpeechEmoRecogRP.getDefaultInstance()) return this;
      if (other.hasErkMsgDataHead()) {
        mergeErkMsgDataHead(other.getErkMsgDataHead());
      }
      if (other.returnCode_ != 0) {
        setReturnCodeValue(other.getReturnCodeValue());
      }
      if (other.getEmoRecogTime() != 0L) {
        setEmoRecogTime(other.getEmoRecogTime());
      }
      if (other.emotion_ != 0) {
        setEmotionValue(other.getEmotionValue());
      }
      if (other.getAccuracy() != 0F) {
        setAccuracy(other.getAccuracy());
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
              input.readMessage(
                  getErkMsgDataHeadFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 16: {
              returnCode_ = input.readEnum();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            case 24: {
              emoRecogTime_ = input.readInt64();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            case 32: {
              emotion_ = input.readEnum();
              bitField0_ |= 0x00000008;
              break;
            } // case 32
            case 45: {
              accuracy_ = input.readFloat();
              bitField0_ |= 0x00000010;
              break;
            } // case 45
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

    private com.erksystem.protobuf.api.ErkMsgDataHeader erkMsgDataHead_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.erksystem.protobuf.api.ErkMsgDataHeader, com.erksystem.protobuf.api.ErkMsgDataHeader.Builder, com.erksystem.protobuf.api.ErkMsgDataHeaderOrBuilder> erkMsgDataHeadBuilder_;
    /**
     * <code>.ErkMsgDataHeader ErkMsgDataHead = 1;</code>
     * @return Whether the erkMsgDataHead field is set.
     */
    public boolean hasErkMsgDataHead() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>.ErkMsgDataHeader ErkMsgDataHead = 1;</code>
     * @return The erkMsgDataHead.
     */
    public com.erksystem.protobuf.api.ErkMsgDataHeader getErkMsgDataHead() {
      if (erkMsgDataHeadBuilder_ == null) {
        return erkMsgDataHead_ == null ? com.erksystem.protobuf.api.ErkMsgDataHeader.getDefaultInstance() : erkMsgDataHead_;
      } else {
        return erkMsgDataHeadBuilder_.getMessage();
      }
    }
    /**
     * <code>.ErkMsgDataHeader ErkMsgDataHead = 1;</code>
     */
    public Builder setErkMsgDataHead(com.erksystem.protobuf.api.ErkMsgDataHeader value) {
      if (erkMsgDataHeadBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        erkMsgDataHead_ = value;
      } else {
        erkMsgDataHeadBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.ErkMsgDataHeader ErkMsgDataHead = 1;</code>
     */
    public Builder setErkMsgDataHead(
        com.erksystem.protobuf.api.ErkMsgDataHeader.Builder builderForValue) {
      if (erkMsgDataHeadBuilder_ == null) {
        erkMsgDataHead_ = builderForValue.build();
      } else {
        erkMsgDataHeadBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.ErkMsgDataHeader ErkMsgDataHead = 1;</code>
     */
    public Builder mergeErkMsgDataHead(com.erksystem.protobuf.api.ErkMsgDataHeader value) {
      if (erkMsgDataHeadBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
          erkMsgDataHead_ != null &&
          erkMsgDataHead_ != com.erksystem.protobuf.api.ErkMsgDataHeader.getDefaultInstance()) {
          getErkMsgDataHeadBuilder().mergeFrom(value);
        } else {
          erkMsgDataHead_ = value;
        }
      } else {
        erkMsgDataHeadBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.ErkMsgDataHeader ErkMsgDataHead = 1;</code>
     */
    public Builder clearErkMsgDataHead() {
      bitField0_ = (bitField0_ & ~0x00000001);
      erkMsgDataHead_ = null;
      if (erkMsgDataHeadBuilder_ != null) {
        erkMsgDataHeadBuilder_.dispose();
        erkMsgDataHeadBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.ErkMsgDataHeader ErkMsgDataHead = 1;</code>
     */
    public com.erksystem.protobuf.api.ErkMsgDataHeader.Builder getErkMsgDataHeadBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getErkMsgDataHeadFieldBuilder().getBuilder();
    }
    /**
     * <code>.ErkMsgDataHeader ErkMsgDataHead = 1;</code>
     */
    public com.erksystem.protobuf.api.ErkMsgDataHeaderOrBuilder getErkMsgDataHeadOrBuilder() {
      if (erkMsgDataHeadBuilder_ != null) {
        return erkMsgDataHeadBuilder_.getMessageOrBuilder();
      } else {
        return erkMsgDataHead_ == null ?
            com.erksystem.protobuf.api.ErkMsgDataHeader.getDefaultInstance() : erkMsgDataHead_;
      }
    }
    /**
     * <code>.ErkMsgDataHeader ErkMsgDataHead = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.erksystem.protobuf.api.ErkMsgDataHeader, com.erksystem.protobuf.api.ErkMsgDataHeader.Builder, com.erksystem.protobuf.api.ErkMsgDataHeaderOrBuilder> 
        getErkMsgDataHeadFieldBuilder() {
      if (erkMsgDataHeadBuilder_ == null) {
        erkMsgDataHeadBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.erksystem.protobuf.api.ErkMsgDataHeader, com.erksystem.protobuf.api.ErkMsgDataHeader.Builder, com.erksystem.protobuf.api.ErkMsgDataHeaderOrBuilder>(
                getErkMsgDataHead(),
                getParentForChildren(),
                isClean());
        erkMsgDataHead_ = null;
      }
      return erkMsgDataHeadBuilder_;
    }

    private int returnCode_ = 0;
    /**
     * <code>.ReturnCode_e ReturnCode = 2;</code>
     * @return The enum numeric value on the wire for returnCode.
     */
    @java.lang.Override public int getReturnCodeValue() {
      return returnCode_;
    }
    /**
     * <code>.ReturnCode_e ReturnCode = 2;</code>
     * @param value The enum numeric value on the wire for returnCode to set.
     * @return This builder for chaining.
     */
    public Builder setReturnCodeValue(int value) {
      returnCode_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.ReturnCode_e ReturnCode = 2;</code>
     * @return The returnCode.
     */
    @java.lang.Override
    public com.erksystem.protobuf.api.ReturnCode_e getReturnCode() {
      com.erksystem.protobuf.api.ReturnCode_e result = com.erksystem.protobuf.api.ReturnCode_e.forNumber(returnCode_);
      return result == null ? com.erksystem.protobuf.api.ReturnCode_e.UNRECOGNIZED : result;
    }
    /**
     * <code>.ReturnCode_e ReturnCode = 2;</code>
     * @param value The returnCode to set.
     * @return This builder for chaining.
     */
    public Builder setReturnCode(com.erksystem.protobuf.api.ReturnCode_e value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000002;
      returnCode_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.ReturnCode_e ReturnCode = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearReturnCode() {
      bitField0_ = (bitField0_ & ~0x00000002);
      returnCode_ = 0;
      onChanged();
      return this;
    }

    private long emoRecogTime_ ;
    /**
     * <code>int64 EmoRecogTime = 3;</code>
     * @return The emoRecogTime.
     */
    @java.lang.Override
    public long getEmoRecogTime() {
      return emoRecogTime_;
    }
    /**
     * <code>int64 EmoRecogTime = 3;</code>
     * @param value The emoRecogTime to set.
     * @return This builder for chaining.
     */
    public Builder setEmoRecogTime(long value) {

      emoRecogTime_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>int64 EmoRecogTime = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearEmoRecogTime() {
      bitField0_ = (bitField0_ & ~0x00000004);
      emoRecogTime_ = 0L;
      onChanged();
      return this;
    }

    private int emotion_ = 0;
    /**
     * <code>.EmotionType_e Emotion = 4;</code>
     * @return The enum numeric value on the wire for emotion.
     */
    @java.lang.Override public int getEmotionValue() {
      return emotion_;
    }
    /**
     * <code>.EmotionType_e Emotion = 4;</code>
     * @param value The enum numeric value on the wire for emotion to set.
     * @return This builder for chaining.
     */
    public Builder setEmotionValue(int value) {
      emotion_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>.EmotionType_e Emotion = 4;</code>
     * @return The emotion.
     */
    @java.lang.Override
    public com.erksystem.protobuf.api.EmotionType_e getEmotion() {
      com.erksystem.protobuf.api.EmotionType_e result = com.erksystem.protobuf.api.EmotionType_e.forNumber(emotion_);
      return result == null ? com.erksystem.protobuf.api.EmotionType_e.UNRECOGNIZED : result;
    }
    /**
     * <code>.EmotionType_e Emotion = 4;</code>
     * @param value The emotion to set.
     * @return This builder for chaining.
     */
    public Builder setEmotion(com.erksystem.protobuf.api.EmotionType_e value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000008;
      emotion_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.EmotionType_e Emotion = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearEmotion() {
      bitField0_ = (bitField0_ & ~0x00000008);
      emotion_ = 0;
      onChanged();
      return this;
    }

    private float accuracy_ ;
    /**
     * <code>float Accuracy = 5;</code>
     * @return The accuracy.
     */
    @java.lang.Override
    public float getAccuracy() {
      return accuracy_;
    }
    /**
     * <code>float Accuracy = 5;</code>
     * @param value The accuracy to set.
     * @return This builder for chaining.
     */
    public Builder setAccuracy(float value) {

      accuracy_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    /**
     * <code>float Accuracy = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearAccuracy() {
      bitField0_ = (bitField0_ & ~0x00000010);
      accuracy_ = 0F;
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


    // @@protoc_insertion_point(builder_scope:SpeechEmoRecogRP)
  }

  // @@protoc_insertion_point(class_scope:SpeechEmoRecogRP)
  private static final com.erksystem.protobuf.api.SpeechEmoRecogRP DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.erksystem.protobuf.api.SpeechEmoRecogRP();
  }

  public static com.erksystem.protobuf.api.SpeechEmoRecogRP getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SpeechEmoRecogRP>
      PARSER = new com.google.protobuf.AbstractParser<SpeechEmoRecogRP>() {
    @java.lang.Override
    public SpeechEmoRecogRP parsePartialFrom(
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

  public static com.google.protobuf.Parser<SpeechEmoRecogRP> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SpeechEmoRecogRP> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.erksystem.protobuf.api.SpeechEmoRecogRP getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

