package greachconf.vm;

import javax.annotation.Nonnull;

public class AgendaItem {
    @Nonnull
    private String title;

    public AgendaItem() {}

    public AgendaItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AgendaItem that = (AgendaItem) o;

        return title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
