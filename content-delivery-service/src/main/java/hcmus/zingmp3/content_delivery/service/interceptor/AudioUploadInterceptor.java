package hcmus.zingmp3.content_delivery.service.interceptor;

import hcmus.zingmp3.audio.AudioFileInfo;
import io.grpc.*;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;

import static hcmus.zingmp3.Constants.AUDIO_META_CONTEXT;
import static hcmus.zingmp3.Constants.AUDIO_META_KEY;

@GrpcGlobalServerInterceptor
public class AudioUploadInterceptor implements ServerInterceptor {
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        AudioFileInfo audioInfo = null;
        if (metadata.containsKey(AUDIO_META_KEY)) {
            byte[] metaBytes = metadata.get(AUDIO_META_KEY);
            try {
                audioInfo = AudioFileInfo.parseFrom(metaBytes);
            } catch (Exception e) {
                Status status = Status.INTERNAL.withDescription("unable to create audio file metadata");
                serverCall.close(status, metadata);
                return new ServerCall.Listener<>() {};
            }

            Context context = Context.current().withValue(
                    AUDIO_META_CONTEXT,
                    audioInfo
            );
            return Contexts.interceptCall(context, serverCall, metadata, serverCallHandler);
        }
        return serverCallHandler.startCall(serverCall, metadata);
    }
}