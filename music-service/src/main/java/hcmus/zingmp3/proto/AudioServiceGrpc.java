package hcmus.zingmp3.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: GrpcAudio.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AudioServiceGrpc {

  private AudioServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "hcmus.zingmp3.proto.AudioService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<hcmus.zingmp3.proto.AudioFileInfo,
      hcmus.zingmp3.proto.AudioFileInfo> getGetByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getById",
      requestType = hcmus.zingmp3.proto.AudioFileInfo.class,
      responseType = hcmus.zingmp3.proto.AudioFileInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<hcmus.zingmp3.proto.AudioFileInfo,
      hcmus.zingmp3.proto.AudioFileInfo> getGetByIdMethod() {
    io.grpc.MethodDescriptor<hcmus.zingmp3.proto.AudioFileInfo, hcmus.zingmp3.proto.AudioFileInfo> getGetByIdMethod;
    if ((getGetByIdMethod = AudioServiceGrpc.getGetByIdMethod) == null) {
      synchronized (AudioServiceGrpc.class) {
        if ((getGetByIdMethod = AudioServiceGrpc.getGetByIdMethod) == null) {
          AudioServiceGrpc.getGetByIdMethod = getGetByIdMethod =
              io.grpc.MethodDescriptor.<hcmus.zingmp3.proto.AudioFileInfo, hcmus.zingmp3.proto.AudioFileInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.proto.AudioFileInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.proto.AudioFileInfo.getDefaultInstance()))
              .setSchemaDescriptor(new AudioServiceMethodDescriptorSupplier("getById"))
              .build();
        }
      }
    }
    return getGetByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<hcmus.zingmp3.proto.AudioRequest,
      hcmus.zingmp3.proto.AudioResponse> getGetOrCreateIfNotExistsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getOrCreateIfNotExists",
      requestType = hcmus.zingmp3.proto.AudioRequest.class,
      responseType = hcmus.zingmp3.proto.AudioResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<hcmus.zingmp3.proto.AudioRequest,
      hcmus.zingmp3.proto.AudioResponse> getGetOrCreateIfNotExistsMethod() {
    io.grpc.MethodDescriptor<hcmus.zingmp3.proto.AudioRequest, hcmus.zingmp3.proto.AudioResponse> getGetOrCreateIfNotExistsMethod;
    if ((getGetOrCreateIfNotExistsMethod = AudioServiceGrpc.getGetOrCreateIfNotExistsMethod) == null) {
      synchronized (AudioServiceGrpc.class) {
        if ((getGetOrCreateIfNotExistsMethod = AudioServiceGrpc.getGetOrCreateIfNotExistsMethod) == null) {
          AudioServiceGrpc.getGetOrCreateIfNotExistsMethod = getGetOrCreateIfNotExistsMethod =
              io.grpc.MethodDescriptor.<hcmus.zingmp3.proto.AudioRequest, hcmus.zingmp3.proto.AudioResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getOrCreateIfNotExists"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.proto.AudioRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.proto.AudioResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AudioServiceMethodDescriptorSupplier("getOrCreateIfNotExists"))
              .build();
        }
      }
    }
    return getGetOrCreateIfNotExistsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AudioServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AudioServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AudioServiceStub>() {
        @java.lang.Override
        public AudioServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AudioServiceStub(channel, callOptions);
        }
      };
    return AudioServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AudioServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AudioServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AudioServiceBlockingStub>() {
        @java.lang.Override
        public AudioServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AudioServiceBlockingStub(channel, callOptions);
        }
      };
    return AudioServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AudioServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AudioServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AudioServiceFutureStub>() {
        @java.lang.Override
        public AudioServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AudioServiceFutureStub(channel, callOptions);
        }
      };
    return AudioServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getById(hcmus.zingmp3.proto.AudioFileInfo request,
        io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.AudioFileInfo> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetByIdMethod(), responseObserver);
    }

    /**
     */
    default io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.AudioRequest> getOrCreateIfNotExists(
        io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.AudioResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getGetOrCreateIfNotExistsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AudioService.
   */
  public static abstract class AudioServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AudioServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AudioService.
   */
  public static final class AudioServiceStub
      extends io.grpc.stub.AbstractAsyncStub<AudioServiceStub> {
    private AudioServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AudioServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AudioServiceStub(channel, callOptions);
    }

    /**
     */
    public void getById(hcmus.zingmp3.proto.AudioFileInfo request,
        io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.AudioFileInfo> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.AudioRequest> getOrCreateIfNotExists(
        io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.AudioResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getGetOrCreateIfNotExistsMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AudioService.
   */
  public static final class AudioServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AudioServiceBlockingStub> {
    private AudioServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AudioServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AudioServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public hcmus.zingmp3.proto.AudioFileInfo getById(hcmus.zingmp3.proto.AudioFileInfo request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetByIdMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AudioService.
   */
  public static final class AudioServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<AudioServiceFutureStub> {
    private AudioServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AudioServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AudioServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hcmus.zingmp3.proto.AudioFileInfo> getById(
        hcmus.zingmp3.proto.AudioFileInfo request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetByIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BY_ID = 0;
  private static final int METHODID_GET_OR_CREATE_IF_NOT_EXISTS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BY_ID:
          serviceImpl.getById((hcmus.zingmp3.proto.AudioFileInfo) request,
              (io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.AudioFileInfo>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_OR_CREATE_IF_NOT_EXISTS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getOrCreateIfNotExists(
              (io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.AudioResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              hcmus.zingmp3.proto.AudioFileInfo,
              hcmus.zingmp3.proto.AudioFileInfo>(
                service, METHODID_GET_BY_ID)))
        .addMethod(
          getGetOrCreateIfNotExistsMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              hcmus.zingmp3.proto.AudioRequest,
              hcmus.zingmp3.proto.AudioResponse>(
                service, METHODID_GET_OR_CREATE_IF_NOT_EXISTS)))
        .build();
  }

  private static abstract class AudioServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AudioServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return hcmus.zingmp3.proto.Audio.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AudioService");
    }
  }

  private static final class AudioServiceFileDescriptorSupplier
      extends AudioServiceBaseDescriptorSupplier {
    AudioServiceFileDescriptorSupplier() {}
  }

  private static final class AudioServiceMethodDescriptorSupplier
      extends AudioServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AudioServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AudioServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AudioServiceFileDescriptorSupplier())
              .addMethod(getGetByIdMethod())
              .addMethod(getGetOrCreateIfNotExistsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
