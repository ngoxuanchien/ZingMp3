package hcmus.zingmp3.handler;

import com.google.gson.JsonObject;

public interface EventHandler {
    void handle(
            JsonObject object
    );
}
