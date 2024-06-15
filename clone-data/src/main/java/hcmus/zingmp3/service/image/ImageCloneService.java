package hcmus.zingmp3.service.image;

import java.util.UUID;

public interface ImageCloneService {
    UUID cloneImage(String fileName, String url);
}
