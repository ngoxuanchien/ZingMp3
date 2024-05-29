package hcmus.zingmp3.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: GrpcSong.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SongServiceGrpc {

  private SongServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "hcmus.zingmp3.proto.SongService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<hcmus.zingmp3.proto.SongId,
      hcmus.zingmp3.proto.SongResponse> getGetSongByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getSongById",
      requestType = hcmus.zingmp3.proto.SongId.class,
      responseType = hcmus.zingmp3.proto.SongResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<hcmus.zingmp3.proto.SongId,
      hcmus.zingmp3.proto.SongResponse> getGetSongByIdMethod() {
    io.grpc.MethodDescriptor<hcmus.zingmp3.proto.SongId, hcmus.zingmp3.proto.SongResponse> getGetSongByIdMethod;
    if ((getGetSongByIdMethod = SongServiceGrpc.getGetSongByIdMethod) == null) {
      synchronized (SongServiceGrpc.class) {
        if ((getGetSongByIdMethod = SongServiceGrpc.getGetSongByIdMethod) == null) {
          SongServiceGrpc.getGetSongByIdMethod = getGetSongByIdMethod =
              io.grpc.MethodDescriptor.<hcmus.zingmp3.proto.SongId, hcmus.zingmp3.proto.SongResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getSongById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.proto.SongId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.proto.SongResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SongServiceMethodDescriptorSupplier("getSongById"))
              .build();
        }
      }
    }
    return getGetSongByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<hcmus.zingmp3.proto.SongAlias,
      hcmus.zingmp3.proto.SongId> getGetSongIdByAliasMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getSongIdByAlias",
      requestType = hcmus.zingmp3.proto.SongAlias.class,
      responseType = hcmus.zingmp3.proto.SongId.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<hcmus.zingmp3.proto.SongAlias,
      hcmus.zingmp3.proto.SongId> getGetSongIdByAliasMethod() {
    io.grpc.MethodDescriptor<hcmus.zingmp3.proto.SongAlias, hcmus.zingmp3.proto.SongId> getGetSongIdByAliasMethod;
    if ((getGetSongIdByAliasMethod = SongServiceGrpc.getGetSongIdByAliasMethod) == null) {
      synchronized (SongServiceGrpc.class) {
        if ((getGetSongIdByAliasMethod = SongServiceGrpc.getGetSongIdByAliasMethod) == null) {
          SongServiceGrpc.getGetSongIdByAliasMethod = getGetSongIdByAliasMethod =
              io.grpc.MethodDescriptor.<hcmus.zingmp3.proto.SongAlias, hcmus.zingmp3.proto.SongId>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getSongIdByAlias"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.proto.SongAlias.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.proto.SongId.getDefaultInstance()))
              .setSchemaDescriptor(new SongServiceMethodDescriptorSupplier("getSongIdByAlias"))
              .build();
        }
      }
    }
    return getGetSongIdByAliasMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SongServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SongServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SongServiceStub>() {
        @java.lang.Override
        public SongServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SongServiceStub(channel, callOptions);
        }
      };
    return SongServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SongServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SongServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SongServiceBlockingStub>() {
        @java.lang.Override
        public SongServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SongServiceBlockingStub(channel, callOptions);
        }
      };
    return SongServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SongServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SongServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SongServiceFutureStub>() {
        @java.lang.Override
        public SongServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SongServiceFutureStub(channel, callOptions);
        }
      };
    return SongServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getSongById(hcmus.zingmp3.proto.SongId request,
        io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.SongResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetSongByIdMethod(), responseObserver);
    }

    /**
     */
    default void getSongIdByAlias(hcmus.zingmp3.proto.SongAlias request,
        io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.SongId> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetSongIdByAliasMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service SongService.
   */
  public static abstract class SongServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return SongServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service SongService.
   */
  public static final class SongServiceStub
      extends io.grpc.stub.AbstractAsyncStub<SongServiceStub> {
    private SongServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SongServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SongServiceStub(channel, callOptions);
    }

    /**
     */
    public void getSongById(hcmus.zingmp3.proto.SongId request,
        io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.SongResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetSongByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSongIdByAlias(hcmus.zingmp3.proto.SongAlias request,
        io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.SongId> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetSongIdByAliasMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service SongService.
   */
  public static final class SongServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SongServiceBlockingStub> {
    private SongServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SongServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SongServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public hcmus.zingmp3.proto.SongResponse getSongById(hcmus.zingmp3.proto.SongId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetSongByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public hcmus.zingmp3.proto.SongId getSongIdByAlias(hcmus.zingmp3.proto.SongAlias request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetSongIdByAliasMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service SongService.
   */
  public static final class SongServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<SongServiceFutureStub> {
    private SongServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SongServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SongServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hcmus.zingmp3.proto.SongResponse> getSongById(
        hcmus.zingmp3.proto.SongId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetSongByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hcmus.zingmp3.proto.SongId> getSongIdByAlias(
        hcmus.zingmp3.proto.SongAlias request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetSongIdByAliasMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_SONG_BY_ID = 0;
  private static final int METHODID_GET_SONG_ID_BY_ALIAS = 1;

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
        case METHODID_GET_SONG_BY_ID:
          serviceImpl.getSongById((hcmus.zingmp3.proto.SongId) request,
              (io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.SongResponse>) responseObserver);
          break;
        case METHODID_GET_SONG_ID_BY_ALIAS:
          serviceImpl.getSongIdByAlias((hcmus.zingmp3.proto.SongAlias) request,
              (io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.SongId>) responseObserver);
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
          getGetSongByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              hcmus.zingmp3.proto.SongId,
              hcmus.zingmp3.proto.SongResponse>(
                service, METHODID_GET_SONG_BY_ID)))
        .addMethod(
          getGetSongIdByAliasMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              hcmus.zingmp3.proto.SongAlias,
              hcmus.zingmp3.proto.SongId>(
                service, METHODID_GET_SONG_ID_BY_ALIAS)))
        .build();
  }

  private static abstract class SongServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SongServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return hcmus.zingmp3.proto.Song.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SongService");
    }
  }

  private static final class SongServiceFileDescriptorSupplier
      extends SongServiceBaseDescriptorSupplier {
    SongServiceFileDescriptorSupplier() {}
  }

  private static final class SongServiceMethodDescriptorSupplier
      extends SongServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    SongServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (SongServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SongServiceFileDescriptorSupplier())
              .addMethod(getGetSongByIdMethod())
              .addMethod(getGetSongIdByAliasMethod())
              .build();
        }
      }
    }
    return result;
  }
}
