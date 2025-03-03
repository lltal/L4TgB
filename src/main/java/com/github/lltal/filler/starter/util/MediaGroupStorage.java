package com.github.lltal.filler.starter.util;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaVideo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MediaGroupStorage {
    public static Comparator<PhotoSize> photoSizeComparator = Comparator.comparingInt((ps) -> {
        return ps.getWidth() * ps.getHeight();
    });
    private final Map<String, List<Message>> albums;

    public MediaGroupStorage(long time, TimeUnit timeUnit) {
        this.albums = new HashMap<>();
    }

    public boolean handleUpdate(Update update) {
        if (!update.hasMessage()) {
            return false;
        } else {
            String albumId = update.getMessage().getMediaGroupId();
            if (albumId == null) {
                return false;
            } else {
                List<Message> messages = (List)this.albums.computeIfAbsent(albumId, (k) -> {
                    return new ArrayList();
                });
                messages.add(update.getMessage());
                return true;
            }
        }
    }

    public boolean hasMediaGroup(String id) {
        return this.albums.containsKey(id);
    }

    public List<Message> getAll(String id) {
        return (List)this.albums.get(id);
    }

    public List<InputMedia> getMedia(String id) {
        return (List)((List)this.albums.getOrDefault(id, List.of())).stream().map((m) -> toInputMedia((Message) m)).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private InputMedia toInputMedia(Message msg) {
        if (msg.hasPhoto()) {
            PhotoSize photoSize = (PhotoSize)msg.getPhoto().stream().max(photoSizeComparator).orElse(null);
            if (photoSize != null) {
                InputMediaPhoto im = new InputMediaPhoto(photoSize.getFileId());
                im.setCaption(msg.getCaption());
                return im;
            }
        }

        if (msg.hasVideo()) {
            InputMediaVideo im = new InputMediaVideo(msg.getVideo().getFileId());
            im.setCaption(msg.getCaption());
            return im;
        } else {
            return null;
        }
    }
}

