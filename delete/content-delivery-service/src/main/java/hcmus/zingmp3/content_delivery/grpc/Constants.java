package hcmus.zingmp3.content_delivery.grpc;

import hcmus.zingmp3.proto.AudioFileInfo;
import hcmus.zingmp3.proto.ImageFileInfo;
import io.grpc.Context;
import io.grpc.Metadata;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

public class Constants {
    public static final Metadata.Key<byte[]> IMAGE_META_KEY = Metadata.Key.of("image-meta-bin", Metadata.BINARY_BYTE_MARSHALLER);
    public static final Context.Key<ImageFileInfo> IMAGE_META_CONTEXT = Context.key("image-meta");
    public static final Metadata.Key<byte[]> AUDIO_META_KEY = Metadata.Key.of("audio-meta-bin", Metadata.BINARY_BYTE_MARSHALLER);
    public static final Context.Key<AudioFileInfo> AUDIO_META_CONTEXT = Context.key("audio-meta");

    public static final String REFRESH_SUFFIX = "+1";
    public static final String ACCESS_TOKEN = "access-token";
    public static final Context.Key<String> CLIENT_ID_CONTEXT_KEY = Context.key("clientId");
    public static final Metadata.Key<String> AUTHORIZATION_METADATA_KEY = Metadata.Key.of("Authorization", ASCII_STRING_MARSHALLER);
}
