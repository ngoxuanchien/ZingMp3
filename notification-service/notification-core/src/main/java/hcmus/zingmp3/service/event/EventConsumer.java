package hcmus.zingmp3.service.event;

public interface EventConsumer {
    void processEvent(String payload);
}
