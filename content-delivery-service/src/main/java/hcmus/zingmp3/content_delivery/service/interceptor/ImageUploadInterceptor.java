package hcmus.zingmp3.content_delivery.service.interceptor;

import hcmus.zingmp3.proto.ImageFileInfo;
import io.grpc.*;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;

import static hcmus.zingmp3.content_delivery.grpc.Constants.IMAGE_META_CONTEXT;
import static hcmus.zingmp3.content_delivery.grpc.Constants.IMAGE_META_KEY;

@GrpcGlobalServerInterceptor
public class ImageUploadInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        ImageFileInfo imageFileInfo = null;
        if (metadata.containsKey(IMAGE_META_KEY)) {
            byte[] metaBytes = metadata.get(IMAGE_META_KEY);
            try {
                imageFileInfo = ImageFileInfo.parseFrom(metaBytes);
            } catch (Exception e) {
                Status status = Status.INTERNAL.withDescription("unable to create image file metadata");
                serverCall.close(status, metadata);
                return new ServerCall.Listener<>() {};
            }

            Context context = Context.current().withValue(
                    IMAGE_META_CONTEXT,
                    imageFileInfo
            );
            return Contexts.interceptCall(context, serverCall, metadata, serverCallHandler);
        }
        return serverCallHandler.startCall(serverCall, metadata);
    }
}