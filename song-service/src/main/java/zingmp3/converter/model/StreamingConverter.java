package zingmp3.converter.model;

import org.springframework.stereotype.Component;
import zingmp3.dto.StreamingDto;
import zingmp3.model.Streaming;

@Component
public class StreamingConverter implements Converter<Streaming, StreamingDto> {
    @Override
    public StreamingDto convert(Streaming streaming) {
        return StreamingDto.builder()
                .id(streaming.getId())
                .url128kps(streaming.getUrl128kps())
                .url320kps(streaming.getUrl320kps())
                .build();
    }

    @Override
    public Streaming reverseConvert(StreamingDto streaming) {
        return Streaming.builder()
                .id(streaming.getId())
                .url128kps(streaming.getUrl128kps())
                .url320kps(streaming.getUrl320kps())
                .build();
    }
}
