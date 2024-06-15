package hcmus.zingmp3.service.media;

import java.util.UUID;

public interface MediaCloneService {
    UUID cloneMedia(String fileName, String url);
}
