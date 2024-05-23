package hcmus.zingmp3.music_service;

import io.grpc.Context;
import io.grpc.Metadata;

public class Constants {
    public static final Metadata.Key<byte[]> IMAGE_META_KEY = Metadata.Key.of("image-meta-bin", Metadata.BINARY_BYTE_MARSHALLER);
    public static final Context.Key<ImageFileInfo> IMAGE_META_CONTEXT = Context.key("image-meta");
    public static final Metadata.Key<byte[]> AUDIO_META_KEY = Metadata.Key.of("audio-meta-bin", Metadata.BINARY_BYTE_MARSHALLER);
    public static final Context.Key<AudioFileInfo> AUDIO_META_CONTEXT = Context.key("audio-meta");
}
