package hcmus.zingmp3.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: GrpcGenre.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GenreServiceGrpc {

  private GenreServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "hcmus.zingmp3.proto.GenreService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<hcmus.zingmp3.proto.GenreRequest,
      hcmus.zingmp3.proto.GenreResponse> getGetGenreByAliasMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getGenreByAlias",
      requestType = hcmus.zingmp3.proto.GenreRequest.class,
      responseType = hcmus.zingmp3.proto.GenreResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<hcmus.zingmp3.proto.GenreRequest,
      hcmus.zingmp3.proto.GenreResponse> getGetGenreByAliasMethod() {
    io.grpc.MethodDescriptor<hcmus.zingmp3.proto.GenreRequest, hcmus.zingmp3.proto.GenreResponse> getGetGenreByAliasMethod;
    if ((getGetGenreByAliasMethod = GenreServiceGrpc.getGetGenreByAliasMethod) == null) {
      synchronized (GenreServiceGrpc.class) {
        if ((getGetGenreByAliasMethod = GenreServiceGrpc.getGetGenreByAliasMethod) == null) {
          GenreServiceGrpc.getGetGenreByAliasMethod = getGetGenreByAliasMethod =
              io.grpc.MethodDescriptor.<hcmus.zingmp3.proto.GenreRequest, hcmus.zingmp3.proto.GenreResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getGenreByAlias"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.proto.GenreRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.proto.GenreResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GenreServiceMethodDescriptorSupplier("getGenreByAlias"))
              .build();
        }
      }
    }
    return getGetGenreByAliasMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GenreServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenreServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenreServiceStub>() {
        @java.lang.Override
        public GenreServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenreServiceStub(channel, callOptions);
        }
      };
    return GenreServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GenreServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenreServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenreServiceBlockingStub>() {
        @java.lang.Override
        public GenreServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenreServiceBlockingStub(channel, callOptions);
        }
      };
    return GenreServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GenreServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenreServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenreServiceFutureStub>() {
        @java.lang.Override
        public GenreServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenreServiceFutureStub(channel, callOptions);
        }
      };
    return GenreServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getGenreByAlias(hcmus.zingmp3.proto.GenreRequest request,
        io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.GenreResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetGenreByAliasMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service GenreService.
   */
  public static abstract class GenreServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return GenreServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service GenreService.
   */
  public static final class GenreServiceStub
      extends io.grpc.stub.AbstractAsyncStub<GenreServiceStub> {
    private GenreServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenreServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenreServiceStub(channel, callOptions);
    }

    /**
     */
    public void getGenreByAlias(hcmus.zingmp3.proto.GenreRequest request,
        io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.GenreResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetGenreByAliasMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service GenreService.
   */
  public static final class GenreServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<GenreServiceBlockingStub> {
    private GenreServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenreServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenreServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public hcmus.zingmp3.proto.GenreResponse getGenreByAlias(hcmus.zingmp3.proto.GenreRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetGenreByAliasMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service GenreService.
   */
  public static final class GenreServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<GenreServiceFutureStub> {
    private GenreServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenreServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenreServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hcmus.zingmp3.proto.GenreResponse> getGenreByAlias(
        hcmus.zingmp3.proto.GenreRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetGenreByAliasMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_GENRE_BY_ALIAS = 0;

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
        case METHODID_GET_GENRE_BY_ALIAS:
          serviceImpl.getGenreByAlias((hcmus.zingmp3.proto.GenreRequest) request,
              (io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.GenreResponse>) responseObserver);
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
          getGetGenreByAliasMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              hcmus.zingmp3.proto.GenreRequest,
              hcmus.zingmp3.proto.GenreResponse>(
                service, METHODID_GET_GENRE_BY_ALIAS)))
        .build();
  }

  private static abstract class GenreServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GenreServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return hcmus.zingmp3.proto.Genre.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GenreService");
    }
  }

  private static final class GenreServiceFileDescriptorSupplier
      extends GenreServiceBaseDescriptorSupplier {
    GenreServiceFileDescriptorSupplier() {}
  }

  private static final class GenreServiceMethodDescriptorSupplier
      extends GenreServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    GenreServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (GenreServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GenreServiceFileDescriptorSupplier())
              .addMethod(getGetGenreByAliasMethod())
              .build();
        }
      }
    }
    return result;
  }
}
