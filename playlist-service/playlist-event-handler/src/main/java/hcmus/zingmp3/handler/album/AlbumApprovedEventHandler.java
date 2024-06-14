package hcmus.zingmp3.handler.album;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hcmus.zingmp3.common.domain.model.Album;
import hcmus.zingmp3.common.events.album.AlbumApprovedEvent;
import hcmus.zingmp3.handler.EventHandler;
import hcmus.zingmp3.service.album.AlbumService;
import hcmus.zingmp3.service.notification.EmailNotificationService;
import hcmus.zingmp3.service.notification.UserNotificationService;
import hcmus.zingmp3.service.notification.UserNotificationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("ALBUM_APPROVED")
@RequiredArgsConstructor
public class AlbumApprovedEventHandler implements EventHandler {
    private final Gson gson;
    private final AlbumService albumService;

    private final EmailNotificationService emailNotificationService;
    private final UserNotificationService userNotificationService;

    @Override
    public void handle(JsonObject json) {
        var event = gson.fromJson(json, AlbumApprovedEvent.class);
        var album = gson.fromJson(gson.toJsonTree(event.getPayload()), Album.class);
        album.setLastModifiedBy(event.getCreatedBy());
        album.setLastModifiedDate(event.getTimestamp());
        albumService.update(album);

        emailNotificationService.sendEmail(album.getCreatedBy(), event.getType().name(), album.getAlias());
        userNotificationService.send(album.getCreatedBy(), event.getType().name(), album.getTitle());
    }
}
