// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protos/res_http_message.proto

package com.insping.libra.proto;

public final class ResHttpMessage {
  private ResHttpMessage() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ResHttpMessageDataOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.ResHttpMessageData)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string httpResMessage = 1;</code>
     */
    java.lang.String getHttpResMessage();
    /**
     * <code>string httpResMessage = 1;</code>
     */
    com.google.protobuf.ByteString
        getHttpResMessageBytes();
  }
  /**
   * <pre>
   * Http回复消息
   * </pre>
   *
   * Protobuf type {@code proto.ResHttpMessageData}
   */
  public  static final class ResHttpMessageData extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:proto.ResHttpMessageData)
      ResHttpMessageDataOrBuilder {
    // Use ResHttpMessageData.newBuilder() to construct.
    private ResHttpMessageData(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ResHttpMessageData() {
      httpResMessage_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private ResHttpMessageData(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              httpResMessage_ = s;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.insping.libra.proto.ResHttpMessage.internal_static_proto_ResHttpMessageData_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.insping.libra.proto.ResHttpMessage.internal_static_proto_ResHttpMessageData_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.insping.libra.proto.ResHttpMessage.ResHttpMessageData.class, com.insping.libra.proto.ResHttpMessage.ResHttpMessageData.Builder.class);
    }

    public static final int HTTPRESMESSAGE_FIELD_NUMBER = 1;
    private volatile java.lang.Object httpResMessage_;
    /**
     * <code>string httpResMessage = 1;</code>
     */
    public java.lang.String getHttpResMessage() {
      java.lang.Object ref = httpResMessage_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        httpResMessage_ = s;
        return s;
      }
    }
    /**
     * <code>string httpResMessage = 1;</code>
     */
    public com.google.protobuf.ByteString
        getHttpResMessageBytes() {
      java.lang.Object ref = httpResMessage_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        httpResMessage_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getHttpResMessageBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, httpResMessage_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getHttpResMessageBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, httpResMessage_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.insping.libra.proto.ResHttpMessage.ResHttpMessageData)) {
        return super.equals(obj);
      }
      com.insping.libra.proto.ResHttpMessage.ResHttpMessageData other = (com.insping.libra.proto.ResHttpMessage.ResHttpMessageData) obj;

      boolean result = true;
      result = result && getHttpResMessage()
          .equals(other.getHttpResMessage());
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + HTTPRESMESSAGE_FIELD_NUMBER;
      hash = (53 * hash) + getHttpResMessage().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.insping.libra.proto.ResHttpMessage.ResHttpMessageData parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.insping.libra.proto.ResHttpMessage.ResHttpMessageData parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.insping.libra.proto.ResHttpMessage.ResHttpMessageData parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.insping.libra.proto.ResHttpMessage.ResHttpMessageData parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.insping.libra.proto.ResHttpMessage.ResHttpMessageData parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.insping.libra.proto.ResHttpMessage.ResHttpMessageData parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.insping.libra.proto.ResHttpMessage.ResHttpMessageData parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.insping.libra.proto.ResHttpMessage.ResHttpMessageData parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.insping.libra.proto.ResHttpMessage.ResHttpMessageData parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.insping.libra.proto.ResHttpMessage.ResHttpMessageData parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.insping.libra.proto.ResHttpMessage.ResHttpMessageData parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.insping.libra.proto.ResHttpMessage.ResHttpMessageData parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.insping.libra.proto.ResHttpMessage.ResHttpMessageData prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
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
     * <pre>
     * Http回复消息
     * </pre>
     *
     * Protobuf type {@code proto.ResHttpMessageData}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.ResHttpMessageData)
        com.insping.libra.proto.ResHttpMessage.ResHttpMessageDataOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.insping.libra.proto.ResHttpMessage.internal_static_proto_ResHttpMessageData_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.insping.libra.proto.ResHttpMessage.internal_static_proto_ResHttpMessageData_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.insping.libra.proto.ResHttpMessage.ResHttpMessageData.class, com.insping.libra.proto.ResHttpMessage.ResHttpMessageData.Builder.class);
      }

      // Construct using ResHttpMessage.ResHttpMessageData.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        httpResMessage_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.insping.libra.proto.ResHttpMessage.internal_static_proto_ResHttpMessageData_descriptor;
      }

      public com.insping.libra.proto.ResHttpMessage.ResHttpMessageData getDefaultInstanceForType() {
        return com.insping.libra.proto.ResHttpMessage.ResHttpMessageData.getDefaultInstance();
      }

      public com.insping.libra.proto.ResHttpMessage.ResHttpMessageData build() {
        com.insping.libra.proto.ResHttpMessage.ResHttpMessageData result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.insping.libra.proto.ResHttpMessage.ResHttpMessageData buildPartial() {
        com.insping.libra.proto.ResHttpMessage.ResHttpMessageData result = new com.insping.libra.proto.ResHttpMessage.ResHttpMessageData(this);
        result.httpResMessage_ = httpResMessage_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.insping.libra.proto.ResHttpMessage.ResHttpMessageData) {
          return mergeFrom((com.insping.libra.proto.ResHttpMessage.ResHttpMessageData)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.insping.libra.proto.ResHttpMessage.ResHttpMessageData other) {
        if (other == com.insping.libra.proto.ResHttpMessage.ResHttpMessageData.getDefaultInstance()) return this;
        if (!other.getHttpResMessage().isEmpty()) {
          httpResMessage_ = other.httpResMessage_;
          onChanged();
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.insping.libra.proto.ResHttpMessage.ResHttpMessageData parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.insping.libra.proto.ResHttpMessage.ResHttpMessageData) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object httpResMessage_ = "";
      /**
       * <code>string httpResMessage = 1;</code>
       */
      public java.lang.String getHttpResMessage() {
        java.lang.Object ref = httpResMessage_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          httpResMessage_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string httpResMessage = 1;</code>
       */
      public com.google.protobuf.ByteString
          getHttpResMessageBytes() {
        java.lang.Object ref = httpResMessage_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          httpResMessage_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string httpResMessage = 1;</code>
       */
      public Builder setHttpResMessage(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        httpResMessage_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string httpResMessage = 1;</code>
       */
      public Builder clearHttpResMessage() {
        
        httpResMessage_ = getDefaultInstance().getHttpResMessage();
        onChanged();
        return this;
      }
      /**
       * <code>string httpResMessage = 1;</code>
       */
      public Builder setHttpResMessageBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        httpResMessage_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:proto.ResHttpMessageData)
    }

    // @@protoc_insertion_point(class_scope:proto.ResHttpMessageData)
    private static final com.insping.libra.proto.ResHttpMessage.ResHttpMessageData DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.insping.libra.proto.ResHttpMessage.ResHttpMessageData();
    }

    public static com.insping.libra.proto.ResHttpMessage.ResHttpMessageData getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ResHttpMessageData>
        PARSER = new com.google.protobuf.AbstractParser<ResHttpMessageData>() {
      public ResHttpMessageData parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new ResHttpMessageData(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ResHttpMessageData> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ResHttpMessageData> getParserForType() {
      return PARSER;
    }

    public com.insping.libra.proto.ResHttpMessage.ResHttpMessageData getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_ResHttpMessageData_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_ResHttpMessageData_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\035protos/res_http_message.proto\022\005proto\"," +
      "\n\022ResHttpMessageData\022\026\n\016httpResMessage\030\001" +
      " \001(\tB=\n\027com.insping.libra.protoB\016ResHttp" +
      "Message\252\002\021LibraClient.protob\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_proto_ResHttpMessageData_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_ResHttpMessageData_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_ResHttpMessageData_descriptor,
        new java.lang.String[] { "HttpResMessage", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
