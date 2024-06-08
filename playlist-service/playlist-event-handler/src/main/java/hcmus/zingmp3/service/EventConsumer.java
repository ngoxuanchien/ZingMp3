package hcmus.zingmp3.service;

public interface EventConsumer {
    void process(String payload);
}
