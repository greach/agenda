package greachconf.vm;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AgendaItem {
    @Nonnull
    private String title;

    @Nullable
    private String location;

    public AgendaItem() {}

    public AgendaItem(String title, String location) {
        this.title = title;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Nullable
    public String getLocation() {
        return location;
    }

    public void setLocation(@Nullable String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AgendaItem that = (AgendaItem) o;

        if (!title.equals(that.title)) return false;
        return location != null ? location.equals(that.location) : that.location == null;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
