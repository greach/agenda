package greachconf.vm;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Talk {

    @Nonnull
    private String uid;

    @Nonnull
    private String title;

    @Nullable
    private List<String> about;

    @Nullable
    private String slides;

    @Nullable
    private String video;

    @Nullable
    private Set<String> speakers = new HashSet<>();

    @Nullable
    private LocalDateTime start;

    @Nullable
    private LocalDateTime end;

    @Nullable
    private String track;

    public Talk() {

    }

    @Nonnull
    public String getUid() {
        return uid;
    }

    public void setUid(@Nonnull String uid) {
        this.uid = uid;
    }

    @Nullable
    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(@Nullable LocalDateTime start) {
        this.start = start;
    }

    @Nullable
    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(@Nullable LocalDateTime end) {
        this.end = end;
    }

    @Nullable
    public String getSlides() {
        return slides;
    }

    public void setSlides(@Nullable String slides) {
        this.slides = slides;
    }

    @Nullable
    public String getVideo() {
        return video;
    }

    public void setVideo(@Nullable String video) {
        this.video = video;
    }

    @Nullable
    public String getTrack() {
        return track;
    }

    public void setTrack(@Nullable String track) {
        this.track = track;
    }

    @Nonnull
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nonnull String title) {
        this.title = title;
    }

    @Nullable
    public List<String> getAbout() {
        return about;
    }

    public void setAbout(@Nullable List<String> about) {
        this.about = about;
    }

    @Nullable
    public Set<String> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(@Nullable Set<String> speakers) {
        this.speakers = speakers;
    }
}
