package hcmus.zingmp3.service;

public interface SongEventConsumer {
    void process(String payload);
}
