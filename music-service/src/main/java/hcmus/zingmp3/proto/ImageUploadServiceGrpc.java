package hcmus.zingmp3.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: GrpcImage.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ImageUploadServiceGrpc {

  private ImageUploadServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "hcmus.zingmp3.proto.ImageUploadService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<hcmus.zingmp3.proto.ImageFileInfo,
      hcmus.zingmp3.proto.ImageFileInfo> getGetByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getById",
      requestType = hcmus.zingmp3.proto.ImageFileInfo.class,
      responseType = hcmus.zingmp3.proto.ImageFileInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<hcmus.zingmp3.proto.ImageFileInfo,
      hcmus.zingmp3.proto.ImageFileInfo> getGetByIdMethod() {
    io.grpc.MethodDescriptor<hcmus.zingmp3.proto.ImageFileInfo, hcmus.zingmp3.proto.ImageFileInfo> getGetByIdMethod;
    if ((getGetByIdMethod = ImageUploadServiceGrpc.getGetByIdMethod) == null) {
      synchronized (ImageUploadServiceGrpc.class) {
        if ((getGetByIdMethod = ImageUploadServiceGrpc.getGetByIdMethod) == null) {
          ImageUploadServiceGrpc.getGetByIdMethod = getGetByIdMethod =
              io.grpc.MethodDescriptor.<hcmus.zingmp3.proto.ImageFileInfo, hcmus.zingmp3.proto.ImageFileInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.proto.ImageFileInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.proto.ImageFileInfo.getDefaultInstance()))
              .setSchemaDescriptor(new ImageUploadServiceMethodDescriptorSupplier("getById"))
              .build();
        }
      }
    }
    return getGetByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<hcmus.zingmp3.proto.ImageUploadRequest,
      hcmus.zingmp3.proto.ImageUploadResponse> getGetOrCreateIfNotExistMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getOrCreateIfNotExist",
      requestType = hcmus.zingmp3.proto.ImageUploadRequest.class,
      responseType = hcmus.zingmp3.proto.ImageUploadResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<hcmus.zingmp3.proto.ImageUploadRequest,
      hcmus.zingmp3.proto.ImageUploadResponse> getGetOrCreateIfNotExistMethod() {
    io.grpc.MethodDescriptor<hcmus.zingmp3.proto.ImageUploadRequest, hcmus.zingmp3.proto.ImageUploadResponse> getGetOrCreateIfNotExistMethod;
    if ((getGetOrCreateIfNotExistMethod = ImageUploadServiceGrpc.getGetOrCreateIfNotExistMethod) == null) {
      synchronized (ImageUploadServiceGrpc.class) {
        if ((getGetOrCreateIfNotExistMethod = ImageUploadServiceGrpc.getGetOrCreateIfNotExistMethod) == null) {
          ImageUploadServiceGrpc.getGetOrCreateIfNotExistMethod = getGetOrCreateIfNotExistMethod =
              io.grpc.MethodDescriptor.<hcmus.zingmp3.proto.ImageUploadRequest, hcmus.zingmp3.proto.ImageUploadResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getOrCreateIfNotExist"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.proto.ImageUploadRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  hcmus.zingmp3.proto.ImageUploadResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ImageUploadServiceMethodDescriptorSupplier("getOrCreateIfNotExist"))
              .build();
        }
      }
    }
    return getGetOrCreateIfNotExistMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ImageUploadServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ImageUploadServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ImageUploadServiceStub>() {
        @java.lang.Override
        public ImageUploadServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ImageUploadServiceStub(channel, callOptions);
        }
      };
    return ImageUploadServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ImageUploadServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ImageUploadServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ImageUploadServiceBlockingStub>() {
        @java.lang.Override
        public ImageUploadServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ImageUploadServiceBlockingStub(channel, callOptions);
        }
      };
    return ImageUploadServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ImageUploadServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ImageUploadServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ImageUploadServiceFutureStub>() {
        @java.lang.Override
        public ImageUploadServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ImageUploadServiceFutureStub(channel, callOptions);
        }
      };
    return ImageUploadServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getById(hcmus.zingmp3.proto.ImageFileInfo request,
        io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.ImageFileInfo> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetByIdMethod(), responseObserver);
    }

    /**
     */
    default io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.ImageUploadRequest> getOrCreateIfNotExist(
        io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.ImageUploadResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getGetOrCreateIfNotExistMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ImageUploadService.
   */
  public static abstract class ImageUploadServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ImageUploadServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ImageUploadService.
   */
  public static final class ImageUploadServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ImageUploadServiceStub> {
    private ImageUploadServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ImageUploadServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ImageUploadServiceStub(channel, callOptions);
    }

    /**
     */
    public void getById(hcmus.zingmp3.proto.ImageFileInfo request,
        io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.ImageFileInfo> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.ImageUploadRequest> getOrCreateIfNotExist(
        io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.ImageUploadResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getGetOrCreateIfNotExistMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ImageUploadService.
   */
  public static final class ImageUploadServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ImageUploadServiceBlockingStub> {
    private ImageUploadServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ImageUploadServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ImageUploadServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public hcmus.zingmp3.proto.ImageFileInfo getById(hcmus.zingmp3.proto.ImageFileInfo request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetByIdMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ImageUploadService.
   */
  public static final class ImageUploadServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ImageUploadServiceFutureStub> {
    private ImageUploadServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ImageUploadServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ImageUploadServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hcmus.zingmp3.proto.ImageFileInfo> getById(
        hcmus.zingmp3.proto.ImageFileInfo request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetByIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BY_ID = 0;
  private static final int METHODID_GET_OR_CREATE_IF_NOT_EXIST = 1;

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
          serviceImpl.getById((hcmus.zingmp3.proto.ImageFileInfo) request,
              (io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.ImageFileInfo>) responseObserver);
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
        case METHODID_GET_OR_CREATE_IF_NOT_EXIST:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getOrCreateIfNotExist(
              (io.grpc.stub.StreamObserver<hcmus.zingmp3.proto.ImageUploadResponse>) responseObserver);
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
              hcmus.zingmp3.proto.ImageFileInfo,
              hcmus.zingmp3.proto.ImageFileInfo>(
                service, METHODID_GET_BY_ID)))
        .addMethod(
          getGetOrCreateIfNotExistMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              hcmus.zingmp3.proto.ImageUploadRequest,
              hcmus.zingmp3.proto.ImageUploadResponse>(
                service, METHODID_GET_OR_CREATE_IF_NOT_EXIST)))
        .build();
  }

  private static abstract class ImageUploadServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ImageUploadServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return hcmus.zingmp3.proto.Image.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ImageUploadService");
    }
  }

  private static final class ImageUploadServiceFileDescriptorSupplier
      extends ImageUploadServiceBaseDescriptorSupplier {
    ImageUploadServiceFileDescriptorSupplier() {}
  }

  private static final class ImageUploadServiceMethodDescriptorSupplier
      extends ImageUploadServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ImageUploadServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ImageUploadServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ImageUploadServiceFileDescriptorSupplier())
              .addMethod(getGetByIdMethod())
              .addMethod(getGetOrCreateIfNotExistMethod())
              .build();
        }
      }
    }
    return result;
  }
}
