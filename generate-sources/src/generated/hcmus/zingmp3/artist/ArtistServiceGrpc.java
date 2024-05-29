package hcmus.zingmp3.artist;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: GrpcArtist.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ArtistServiceGrpc {

  private ArtistServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "hcmus.zingmp3.artist.ArtistService";

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
    if ((getGetArtistByIdMethod = ArtistServiceGrpc.getGetArtistByIdMethod) == null) {
      synchronized (ArtistServiceGrpc.class) {
        if ((getGetArtistByIdMethod = ArtistServiceGrpc.getGetArtistByIdMethod) == null) {
          ArtistServiceGrpc.getGetArtistByIdMethod = getGetArtistByIdMethod =
              io.grpc.MethodDescriptor.<hcmus.zingmp3.artist.ArtistGrpcRequest, hcmus.zingmp3.artist.ArtistGrpcResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getArtistById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.artist.ArtistGrpcRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.artist.ArtistGrpcResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ArtistServiceMethodDescriptorSupplier("getArtistById"))
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
    if ((getGetOrCreateArtistMethod = ArtistServiceGrpc.getGetOrCreateArtistMethod) == null) {
      synchronized (ArtistServiceGrpc.class) {
        if ((getGetOrCreateArtistMethod = ArtistServiceGrpc.getGetOrCreateArtistMethod) == null) {
          ArtistServiceGrpc.getGetOrCreateArtistMethod = getGetOrCreateArtistMethod =
              io.grpc.MethodDescriptor.<hcmus.zingmp3.artist.ArtistGrpcRequest, hcmus.zingmp3.artist.ArtistGrpcResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getOrCreateArtist"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.artist.ArtistGrpcRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.artist.ArtistGrpcResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ArtistServiceMethodDescriptorSupplier("getOrCreateArtist"))
              .build();
        }
      }
    }
    return getGetOrCreateArtistMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ArtistServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ArtistServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ArtistServiceStub>() {
        @java.lang.Override
        public ArtistServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ArtistServiceStub(channel, callOptions);
        }
      };
    return ArtistServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ArtistServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ArtistServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ArtistServiceBlockingStub>() {
        @java.lang.Override
        public ArtistServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ArtistServiceBlockingStub(channel, callOptions);
        }
      };
    return ArtistServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ArtistServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ArtistServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ArtistServiceFutureStub>() {
        @java.lang.Override
        public ArtistServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ArtistServiceFutureStub(channel, callOptions);
        }
      };
    return ArtistServiceFutureStub.newStub(factory, channel);
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
   * Base class for the server implementation of the service ArtistService.
   */
  public static abstract class ArtistServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ArtistServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ArtistService.
   */
  public static final class ArtistServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ArtistServiceStub> {
    private ArtistServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ArtistServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ArtistServiceStub(channel, callOptions);
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
   * A stub to allow clients to do synchronous rpc calls to service ArtistService.
   */
  public static final class ArtistServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ArtistServiceBlockingStub> {
    private ArtistServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ArtistServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ArtistServiceBlockingStub(channel, callOptions);
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
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ArtistService.
   */
  public static final class ArtistServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ArtistServiceFutureStub> {
    private ArtistServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ArtistServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ArtistServiceFutureStub(channel, callOptions);
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

  private static abstract class ArtistServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ArtistServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return hcmus.zingmp3.artist.GrpcArtist.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ArtistService");
    }
  }

  private static final class ArtistServiceFileDescriptorSupplier
      extends ArtistServiceBaseDescriptorSupplier {
    ArtistServiceFileDescriptorSupplier() {}
  }

  private static final class ArtistServiceMethodDescriptorSupplier
      extends ArtistServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ArtistServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ArtistServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ArtistServiceFileDescriptorSupplier())
              .addMethod(getGetArtistByIdMethod())
              .addMethod(getGetOrCreateArtistMethod())
              .build();
        }
      }
    }
    return result;
  }
}
