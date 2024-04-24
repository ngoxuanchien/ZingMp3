package zingmp3.hcmus.playlistservice.converter;

public interface Converter<S, T> {
    T convert(S source);
    S reverseConvert(T target);
}
