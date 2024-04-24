package zingmp3.converter.model;

import org.springframework.stereotype.Component;
import zingmp3.dto.PreviewInfoDto;
import zingmp3.model.PreviewInfo;

@Component
public class PreviewInfoConverter implements Converter<PreviewInfo, PreviewInfoDto> {
    @Override
    public PreviewInfoDto convert(PreviewInfo previewInfo) {
        if (previewInfo == null) {
            return null;
        }

        return PreviewInfoDto.builder()
                .id(previewInfo.getId())
                .startTime(previewInfo.getStartTime())
                .endTime(previewInfo.getEndTime())
                .build();
    }

    @Override
    public PreviewInfo reverseConvert(PreviewInfoDto previewInfo) {
        if (previewInfo == null) {
            return null;
        }

        return PreviewInfo.builder()
                .id(previewInfo.getId())
                .startTime(previewInfo.getStartTime())
                .endTime(previewInfo.getEndTime())
                .build();
    }
}
