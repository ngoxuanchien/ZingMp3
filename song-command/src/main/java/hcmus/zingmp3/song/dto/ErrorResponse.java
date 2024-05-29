package hcmus.zingmp3.song.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

public record ErrorResponse(Map<String, String> errors) {
}
