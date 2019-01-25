package greachconf.vm;

import javax.annotation.Nonnull;
import java.time.LocalTime;

public class TimeSlot implements Comparable<TimeSlot> {
    @Nonnull
    private LocalTime start;

    @Nonnull
    private LocalTime end;

    public TimeSlot(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    @Nonnull
    public LocalTime getStart() {
        return start;
    }

    public void setStart(@Nonnull LocalTime start) {
        this.start = start;
    }

    @Nonnull
    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(@Nonnull LocalTime end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeSlot timeSlot = (TimeSlot) o;

        if (!start.equals(timeSlot.start)) return false;
        return end.equals(timeSlot.end);
    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }

    @Override
    public int compareTo(TimeSlot o) {
        return this.getStart().compareTo(o.getStart());
    }
}
