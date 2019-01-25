package greachconf.vm;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class Agenda {

    @Nonnull
    private List<AgendaDay> days;

    public Agenda(List<AgendaDay> days) {
        this.days = days;
        Collections.sort(days);
    }

    public List<AgendaDay> getDays() {
        return days;
    }

    public void setDays(List<AgendaDay> days) {
        this.days = days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Agenda agenda = (Agenda) o;

        return days.equals(agenda.days);
    }

    @Override
    public int hashCode() {
        return days.hashCode();
    }
}
