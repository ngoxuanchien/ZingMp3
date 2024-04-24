package zingmp3.converter.model;

import org.springframework.stereotype.Component;
import zingmp3.dto.IndicatorDto;
import zingmp3.model.Indicator;

@Component
public class IndicatorConverter implements Converter<Indicator, IndicatorDto> {
    @Override
    public IndicatorDto convert(Indicator indicator) {
        return IndicatorDto.builder()
                .id(indicator.getId())
                .build();
    }

    @Override
    public Indicator reverseConvert(IndicatorDto indicator) {
        return Indicator.builder()
                .id(indicator.getId())
                .build();
    }
}
