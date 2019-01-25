package greachconf.vm;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class AgendaTalk extends AgendaItem {

    @Nonnull
    private String uid;

    @Nullable
    private List<AgendaTalkSpeaker> speakers;

    public AgendaTalk() {}

    public AgendaTalk(String uid, String title, List<AgendaTalkSpeaker> speakers) {
        super(title);
        this.uid = uid;
        this.speakers = speakers;
    }

    @Nonnull
    public String getUid() {
        return uid;
    }

    public void setUid(@Nonnull String uid) {
        this.uid = uid;
    }

    @Nullable
    public List<AgendaTalkSpeaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(@Nullable List<AgendaTalkSpeaker> speakers) {
        this.speakers = speakers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AgendaTalk that = (AgendaTalk) o;

        if (!uid.equals(that.uid)) return false;
        return speakers != null ? speakers.equals(that.speakers) : that.speakers == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + uid.hashCode();
        result = 31 * result + (speakers != null ? speakers.hashCode() : 0);
        return result;
    }
}
