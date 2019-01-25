package greachconf.vm;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AgendaDay implements Comparable<AgendaDay> {

    @Nonnull
    private LocalDate day;

    @Nonnull
    private List<AgendaTimeSlot> timeSlots;

    public AgendaDay() {}

    public AgendaDay(LocalDate day, List<AgendaTimeSlot> timeSlots) {
        this.day = day;
        this.timeSlots = timeSlots;
        Collections.sort(this.timeSlots);
    }

    public Set<String> getTracks() {
        List<Set<String>> result = timeSlots.stream()
                .map(agendaTimeSlot -> agendaTimeSlot.getTrackTalks().keySet())
                .collect(Collectors.toList());
        Set<String> r = new HashSet<>();
        for (Set<String> s : result) {
            r.addAll(s);
        }
        return r;
    }

    @Nonnull
    public LocalDate getDay() {
        return day;
    }

    public void setDay(@Nonnull LocalDate day) {
        this.day = day;
    }

    @Nonnull
    public List<AgendaTimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(@Nonnull List<AgendaTimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AgendaDay agendaDay = (AgendaDay) o;

        if (!day.equals(agendaDay.day)) return false;
        return timeSlots.equals(agendaDay.timeSlots);
    }

    @Override
    public int hashCode() {
        int result = day.hashCode();
        result = 31 * result + timeSlots.hashCode();
        return result;
    }

    @Override
    public int compareTo(AgendaDay o) {
        return this.getDay().compareTo(o.getDay());
    }
}
