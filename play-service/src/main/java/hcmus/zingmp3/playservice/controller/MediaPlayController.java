package hcmus.zingmp3.playservice.controller;

import hcmus.zingmp3.playservice.service.MediaStreamLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/api/play/media")
@RequiredArgsConstructor
public class MediaPlayController
{
    private final MediaStreamLoader mediaLoaderService;

    @GetMapping(value = "/{vid_id}")
    @ResponseBody
    public ResponseEntity<StreamingResponseBody> playMediaV02(
            @PathVariable("vid_id") String video_id,
            @RequestHeader(value = "Range", required = false) String rangeHeader)
    {
        try
        {
            String filePathString = "C:\\Users\\ngoxu\\Downloads\\Âm Thầm Bên Em  OFFICIAL MUSIC VIDEO  Sơn Tùng MTP_320kbps.mp3";
            ResponseEntity<StreamingResponseBody> retVal =
                    mediaLoaderService.loadPartialMediaFile(filePathString, rangeHeader);

            return retVal;
        }
        catch (FileNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IOException e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}