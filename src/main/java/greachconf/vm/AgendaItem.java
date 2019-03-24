package greachconf.vm;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AgendaItem {
    @Nonnull
    private String title;

    @Nullable
    private String location;

    @Nullable
    private String locationLink;

    public AgendaItem() {}

    public AgendaItem(String title, String location, String locationLink) {
        this.title = title;
        this.location = location;
        this.locationLink = locationLink;
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

    @Nullable
    public String getLocationLink() {
        return this.locationLink;
    }

    public void setLocationLink(@Nullable String locationLink) {
        this.locationLink = locationLink;
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
