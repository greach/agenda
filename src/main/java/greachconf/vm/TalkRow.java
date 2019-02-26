package greachconf.vm;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public class TalkRow implements Comparable<TalkRow> {
    @Nonnull
    private String uid;

    @Nonnull
    private String title;

    @Nullable
    private Set<String> speakers = new HashSet<>();

    public TalkRow() {

    }

    public TalkRow(String uid, String title, Set<String> speakers) {
        this.uid = uid;
        this.title = title;
        this.speakers = speakers;
    }

    @Nonnull
    public String getUid() {
        return uid;
    }

    public void setUid(@Nonnull String uid) {
        this.uid = uid;
    }

    @Nonnull
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nonnull String title) {
        this.title = title;
    }

    @Nullable
    public Set<String> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(@Nullable Set<String> speakers) {
        this.speakers = speakers;
    }

    @Override
    public int compareTo(TalkRow o) {
        return this.getTitle().compareTo(o.getTitle());
    }
}
