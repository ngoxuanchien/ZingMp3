package hcmus.zingmp3.artist;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: GrpcArtist.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ArtistGrpcServiceGrpc {

  private ArtistGrpcServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "hcmus.zingmp3.artist.ArtistGrpcService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<hcmus.zingmp3.artist.ArtistGrpcRequest,
      hcmus.zingmp3.artist.ArtistGrpcResponse> getGetArtistByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getArtistById",
      requestType = hcmus.zingmp3.artist.ArtistGrpcRequest.class,
      responseType = hcmus.zingmp3.artist.ArtistGrpcResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<hcmus.zingmp3.artist.ArtistGrpcRequest,
      hcmus.zingmp3.artist.ArtistGrpcResponse> getGetArtistByIdMethod() {
    io.grpc.MethodDescriptor<hcmus.zingmp3.artist.ArtistGrpcRequest, hcmus.zingmp3.artist.ArtistGrpcResponse> getGetArtistByIdMethod;
    if ((getGetArtistByIdMethod = ArtistGrpcServiceGrpc.getGetArtistByIdMethod) == null) {
      synchronized (ArtistGrpcServiceGrpc.class) {
        if ((getGetArtistByIdMethod = ArtistGrpcServiceGrpc.getGetArtistByIdMethod) == null) {
          ArtistGrpcServiceGrpc.getGetArtistByIdMethod = getGetArtistByIdMethod =
              io.grpc.MethodDescriptor.<hcmus.zingmp3.artist.ArtistGrpcRequest, hcmus.zingmp3.artist.ArtistGrpcResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getArtistById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.artist.ArtistGrpcRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.artist.ArtistGrpcResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ArtistGrpcServiceMethodDescriptorSupplier("getArtistById"))
              .build();
        }
      }
    }
    return getGetArtistByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<hcmus.zingmp3.artist.ArtistGrpcRequest,
      hcmus.zingmp3.artist.ArtistGrpcResponse> getGetOrCreateArtistMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getOrCreateArtist",
      requestType = hcmus.zingmp3.artist.ArtistGrpcRequest.class,
      responseType = hcmus.zingmp3.artist.ArtistGrpcResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<hcmus.zingmp3.artist.ArtistGrpcRequest,
      hcmus.zingmp3.artist.ArtistGrpcResponse> getGetOrCreateArtistMethod() {
    io.grpc.MethodDescriptor<hcmus.zingmp3.artist.ArtistGrpcRequest, hcmus.zingmp3.artist.ArtistGrpcResponse> getGetOrCreateArtistMethod;
    if ((getGetOrCreateArtistMethod = ArtistGrpcServiceGrpc.getGetOrCreateArtistMethod) == null) {
      synchronized (ArtistGrpcServiceGrpc.class) {
        if ((getGetOrCreateArtistMethod = ArtistGrpcServiceGrpc.getGetOrCreateArtistMethod) == null) {
          ArtistGrpcServiceGrpc.getGetOrCreateArtistMethod = getGetOrCreateArtistMethod =
              io.grpc.MethodDescriptor.<hcmus.zingmp3.artist.ArtistGrpcRequest, hcmus.zingmp3.artist.ArtistGrpcResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getOrCreateArtist"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.artist.ArtistGrpcRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.artist.ArtistGrpcResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ArtistGrpcServiceMethodDescriptorSupplier("getOrCreateArtist"))
              .build();
        }
      }
    }
    return getGetOrCreateArtistMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ArtistGrpcServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ArtistGrpcServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ArtistGrpcServiceStub>() {
        @java.lang.Override
        public ArtistGrpcServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ArtistGrpcServiceStub(channel, callOptions);
        }
      };
    return ArtistGrpcServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ArtistGrpcServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ArtistGrpcServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ArtistGrpcServiceBlockingStub>() {
        @java.lang.Override
        public ArtistGrpcServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ArtistGrpcServiceBlockingStub(channel, callOptions);
        }
      };
    return ArtistGrpcServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ArtistGrpcServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ArtistGrpcServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ArtistGrpcServiceFutureStub>() {
        @java.lang.Override
        public ArtistGrpcServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ArtistGrpcServiceFutureStub(channel, callOptions);
        }
      };
    return ArtistGrpcServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getArtistById(hcmus.zingmp3.artist.ArtistGrpcRequest request,
        io.grpc.stub.StreamObserver<hcmus.zingmp3.artist.ArtistGrpcResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetArtistByIdMethod(), responseObserver);
    }

    /**
     */
    default void getOrCreateArtist(hcmus.zingmp3.artist.ArtistGrpcRequest request,
        io.grpc.stub.StreamObserver<hcmus.zingmp3.artist.ArtistGrpcResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetOrCreateArtistMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ArtistGrpcService.
   */
  public static abstract class ArtistGrpcServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ArtistGrpcServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ArtistGrpcService.
   */
  public static final class ArtistGrpcServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ArtistGrpcServiceStub> {
    private ArtistGrpcServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ArtistGrpcServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ArtistGrpcServiceStub(channel, callOptions);
    }

    /**
     */
    public void getArtistById(hcmus.zingmp3.artist.ArtistGrpcRequest request,
        io.grpc.stub.StreamObserver<hcmus.zingmp3.artist.ArtistGrpcResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetArtistByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getOrCreateArtist(hcmus.zingmp3.artist.ArtistGrpcRequest request,
        io.grpc.stub.StreamObserver<hcmus.zingmp3.artist.ArtistGrpcResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetOrCreateArtistMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ArtistGrpcService.
   */
  public static final class ArtistGrpcServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ArtistGrpcServiceBlockingStub> {
    private ArtistGrpcServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ArtistGrpcServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ArtistGrpcServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public hcmus.zingmp3.artist.ArtistGrpcResponse getArtistById(hcmus.zingmp3.artist.ArtistGrpcRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetArtistByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public hcmus.zingmp3.artist.ArtistGrpcResponse getOrCreateArtist(hcmus.zingmp3.artist.ArtistGrpcRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetOrCreateArtistMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ArtistGrpcService.
   */
  public static final class ArtistGrpcServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ArtistGrpcServiceFutureStub> {
    private ArtistGrpcServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ArtistGrpcServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ArtistGrpcServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hcmus.zingmp3.artist.ArtistGrpcResponse> getArtistById(
        hcmus.zingmp3.artist.ArtistGrpcRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetArtistByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hcmus.zingmp3.artist.ArtistGrpcResponse> getOrCreateArtist(
        hcmus.zingmp3.artist.ArtistGrpcRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetOrCreateArtistMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ARTIST_BY_ID = 0;
  private static final int METHODID_GET_OR_CREATE_ARTIST = 1;

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
        case METHODID_GET_ARTIST_BY_ID:
          serviceImpl.getArtistById((hcmus.zingmp3.artist.ArtistGrpcRequest) request,
              (io.grpc.stub.StreamObserver<hcmus.zingmp3.artist.ArtistGrpcResponse>) responseObserver);
          break;
        case METHODID_GET_OR_CREATE_ARTIST:
          serviceImpl.getOrCreateArtist((hcmus.zingmp3.artist.ArtistGrpcRequest) request,
              (io.grpc.stub.StreamObserver<hcmus.zingmp3.artist.ArtistGrpcResponse>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetArtistByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              hcmus.zingmp3.artist.ArtistGrpcRequest,
              hcmus.zingmp3.artist.ArtistGrpcResponse>(
                service, METHODID_GET_ARTIST_BY_ID)))
        .addMethod(
          getGetOrCreateArtistMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              hcmus.zingmp3.artist.ArtistGrpcRequest,
              hcmus.zingmp3.artist.ArtistGrpcResponse>(
                service, METHODID_GET_OR_CREATE_ARTIST)))
        .build();
  }

  private static abstract class ArtistGrpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ArtistGrpcServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return hcmus.zingmp3.artist.GrpcArtist.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ArtistGrpcService");
    }
  }

  private static final class ArtistGrpcServiceFileDescriptorSupplier
      extends ArtistGrpcServiceBaseDescriptorSupplier {
    ArtistGrpcServiceFileDescriptorSupplier() {}
  }

  private static final class ArtistGrpcServiceMethodDescriptorSupplier
      extends ArtistGrpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ArtistGrpcServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ArtistGrpcServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ArtistGrpcServiceFileDescriptorSupplier())
              .addMethod(getGetArtistByIdMethod())
              .addMethod(getGetOrCreateArtistMethod())
              .build();
        }
      }
    }
    return result;
  }
}
