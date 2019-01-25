package greachconf.vm;

import javax.annotation.Nonnull;

public class AgendaTalkSpeaker {
    @Nonnull
    private String uid;

    @Nonnull
    private String name;

    public AgendaTalkSpeaker(String uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    @Nonnull
    public String getUid() {
        return uid;
    }

    public void setUid(@Nonnull String uid) {
        this.uid = uid;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(@Nonnull String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AgendaTalkSpeaker that = (AgendaTalkSpeaker) o;

        if (!uid.equals(that.uid)) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = uid.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
