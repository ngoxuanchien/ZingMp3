package zingmp3.converter.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;

public interface Converter<S, T> {
    T convert(S source);

    S reverseConvert(T target);
}
