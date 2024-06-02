package hcmus.zingmp3.content_delivery.controller;

import hcmus.zingmp3.content_delivery.service.MediaStreamLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/content-delivery/play/")
public class MediaPlayController {
    private final MediaStreamLoader mediaStreamLoader;

    @GetMapping(value = "{id}")
    @ResponseBody
    public ResponseEntity<StreamingResponseBody> playMedia(
            @PathVariable("id")
            UUID id,
            @RequestHeader(value = "Range", required = false)
            String rangeHeader)
    {
        return mediaStreamLoader.playMediaFile(id, rangeHeader);
    }
}
