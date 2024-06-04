package hcmus.zingmp3.award;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: GrpcAward.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AwardServiceGrpc {

  private AwardServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "hcmus.zingmp3.award.AwardService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<hcmus.zingmp3.award.AwardGrpcRequest,
      hcmus.zingmp3.award.AwardGrpcResponse> getGetByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getById",
      requestType = hcmus.zingmp3.award.AwardGrpcRequest.class,
      responseType = hcmus.zingmp3.award.AwardGrpcResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<hcmus.zingmp3.award.AwardGrpcRequest,
      hcmus.zingmp3.award.AwardGrpcResponse> getGetByIdMethod() {
    io.grpc.MethodDescriptor<hcmus.zingmp3.award.AwardGrpcRequest, hcmus.zingmp3.award.AwardGrpcResponse> getGetByIdMethod;
    if ((getGetByIdMethod = AwardServiceGrpc.getGetByIdMethod) == null) {
      synchronized (AwardServiceGrpc.class) {
        if ((getGetByIdMethod = AwardServiceGrpc.getGetByIdMethod) == null) {
          AwardServiceGrpc.getGetByIdMethod = getGetByIdMethod =
              io.grpc.MethodDescriptor.<hcmus.zingmp3.award.AwardGrpcRequest, hcmus.zingmp3.award.AwardGrpcResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.award.AwardGrpcRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.award.AwardGrpcResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AwardServiceMethodDescriptorSupplier("getById"))
              .build();
        }
      }
    }
    return getGetByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<hcmus.zingmp3.award.AwardGrpcRequest,
      hcmus.zingmp3.award.AwardGrpcResponse> getGetOrCreateAwardMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getOrCreateAward",
      requestType = hcmus.zingmp3.award.AwardGrpcRequest.class,
      responseType = hcmus.zingmp3.award.AwardGrpcResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<hcmus.zingmp3.award.AwardGrpcRequest,
      hcmus.zingmp3.award.AwardGrpcResponse> getGetOrCreateAwardMethod() {
    io.grpc.MethodDescriptor<hcmus.zingmp3.award.AwardGrpcRequest, hcmus.zingmp3.award.AwardGrpcResponse> getGetOrCreateAwardMethod;
    if ((getGetOrCreateAwardMethod = AwardServiceGrpc.getGetOrCreateAwardMethod) == null) {
      synchronized (AwardServiceGrpc.class) {
        if ((getGetOrCreateAwardMethod = AwardServiceGrpc.getGetOrCreateAwardMethod) == null) {
          AwardServiceGrpc.getGetOrCreateAwardMethod = getGetOrCreateAwardMethod =
              io.grpc.MethodDescriptor.<hcmus.zingmp3.award.AwardGrpcRequest, hcmus.zingmp3.award.AwardGrpcResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getOrCreateAward"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.award.AwardGrpcRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.award.AwardGrpcResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AwardServiceMethodDescriptorSupplier("getOrCreateAward"))
              .build();
        }
      }
    }
    return getGetOrCreateAwardMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AwardServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AwardServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AwardServiceStub>() {
        @java.lang.Override
        public AwardServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AwardServiceStub(channel, callOptions);
        }
      };
    return AwardServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AwardServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AwardServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AwardServiceBlockingStub>() {
        @java.lang.Override
        public AwardServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AwardServiceBlockingStub(channel, callOptions);
        }
      };
    return AwardServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AwardServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AwardServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AwardServiceFutureStub>() {
        @java.lang.Override
        public AwardServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AwardServiceFutureStub(channel, callOptions);
        }
      };
    return AwardServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getById(hcmus.zingmp3.award.AwardGrpcRequest request,
        io.grpc.stub.StreamObserver<hcmus.zingmp3.award.AwardGrpcResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetByIdMethod(), responseObserver);
    }

    /**
     */
    default void getOrCreateAward(hcmus.zingmp3.award.AwardGrpcRequest request,
        io.grpc.stub.StreamObserver<hcmus.zingmp3.award.AwardGrpcResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetOrCreateAwardMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AwardService.
   */
  public static abstract class AwardServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AwardServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AwardService.
   */
  public static final class AwardServiceStub
      extends io.grpc.stub.AbstractAsyncStub<AwardServiceStub> {
    private AwardServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AwardServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AwardServiceStub(channel, callOptions);
    }

    /**
     */
    public void getById(hcmus.zingmp3.award.AwardGrpcRequest request,
        io.grpc.stub.StreamObserver<hcmus.zingmp3.award.AwardGrpcResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getOrCreateAward(hcmus.zingmp3.award.AwardGrpcRequest request,
        io.grpc.stub.StreamObserver<hcmus.zingmp3.award.AwardGrpcResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetOrCreateAwardMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AwardService.
   */
  public static final class AwardServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AwardServiceBlockingStub> {
    private AwardServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AwardServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AwardServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public hcmus.zingmp3.award.AwardGrpcResponse getById(hcmus.zingmp3.award.AwardGrpcRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public hcmus.zingmp3.award.AwardGrpcResponse getOrCreateAward(hcmus.zingmp3.award.AwardGrpcRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetOrCreateAwardMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AwardService.
   */
  public static final class AwardServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<AwardServiceFutureStub> {
    private AwardServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AwardServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AwardServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hcmus.zingmp3.award.AwardGrpcResponse> getById(
        hcmus.zingmp3.award.AwardGrpcRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hcmus.zingmp3.award.AwardGrpcResponse> getOrCreateAward(
        hcmus.zingmp3.award.AwardGrpcRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetOrCreateAwardMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BY_ID = 0;
  private static final int METHODID_GET_OR_CREATE_AWARD = 1;

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
          serviceImpl.getById((hcmus.zingmp3.award.AwardGrpcRequest) request,
              (io.grpc.stub.StreamObserver<hcmus.zingmp3.award.AwardGrpcResponse>) responseObserver);
          break;
        case METHODID_GET_OR_CREATE_AWARD:
          serviceImpl.getOrCreateAward((hcmus.zingmp3.award.AwardGrpcRequest) request,
              (io.grpc.stub.StreamObserver<hcmus.zingmp3.award.AwardGrpcResponse>) responseObserver);
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
          getGetByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              hcmus.zingmp3.award.AwardGrpcRequest,
              hcmus.zingmp3.award.AwardGrpcResponse>(
                service, METHODID_GET_BY_ID)))
        .addMethod(
          getGetOrCreateAwardMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              hcmus.zingmp3.award.AwardGrpcRequest,
              hcmus.zingmp3.award.AwardGrpcResponse>(
                service, METHODID_GET_OR_CREATE_AWARD)))
        .build();
  }

  private static abstract class AwardServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AwardServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return hcmus.zingmp3.award.GrpcAward.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AwardService");
    }
  }

  private static final class AwardServiceFileDescriptorSupplier
      extends AwardServiceBaseDescriptorSupplier {
    AwardServiceFileDescriptorSupplier() {}
  }

  private static final class AwardServiceMethodDescriptorSupplier
      extends AwardServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AwardServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (AwardServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AwardServiceFileDescriptorSupplier())
              .addMethod(getGetByIdMethod())
              .addMethod(getGetOrCreateAwardMethod())
              .build();
        }
      }
    }
    return result;
  }
}
