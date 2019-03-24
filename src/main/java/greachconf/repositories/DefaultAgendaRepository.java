package greachconf.repositories;

import greachconf.configuration.AgendaConfiguration;
import greachconf.vm.Agenda;
import greachconf.vm.AgendaDay;
import greachconf.vm.AgendaItem;
import greachconf.vm.AgendaTalk;
import greachconf.vm.AgendaTalkSpeaker;
import greachconf.vm.AgendaTimeSlot;
import greachconf.vm.Speaker;
import greachconf.vm.Talk;
import greachconf.vm.TimeSlot;
import io.micronaut.validation.Validated;

import javax.annotation.Nonnull;
import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Validated
@Singleton
public class DefaultAgendaRepository implements AgendaRepository {

    private final AgendaConfiguration agendaConfiguration;
    private final SpeakerRepository speakerRepository;

    public DefaultAgendaRepository(AgendaConfiguration agendaConfiguration,
                                   SpeakerRepository speakerRepository) {
        this.agendaConfiguration = agendaConfiguration;
        this.speakerRepository = speakerRepository;
    }

    @Override
    public Agenda findAgenda() {
        Set<LocalDate> days = getDays();
        List<AgendaDay> agendaDays = days.stream().map(localDate -> {
            List<AgendaTimeSlot> timeSlots = findTimesByDate(localDate).stream()
                    .map(timeSlot -> {
                        Map<String, AgendaTalk> trackTalks = findTrackTalksByTimeSlot(localDate, timeSlot);
                        List<AgendaItem> items = findUnTrackTalskByTimeSlot(localDate, timeSlot);
                        return new AgendaTimeSlot(timeSlot, trackTalks, items);
                    }).collect(Collectors.toList());
            return new AgendaDay(localDate, timeSlots);
        }).collect(Collectors.toList());
        return new Agenda(agendaDays);
    }

    private List<Talk> getTalks() {
        return agendaConfiguration.getTalks();
    }

    private List<AgendaItem> findUnTrackTalskByTimeSlot(@Nonnull LocalDate localDate, @Nonnull TimeSlot timeSlot) {
        return getTalks().stream()
                .filter(talk -> talk.getStart()!=null && talk.getEnd() != null && (talk.getTrack() == null || talk.getTrack().isEmpty()))
                .filter(talk -> talk.getStart().toLocalDate().equals(localDate) &&
                        talk.getStart().toLocalTime().equals(timeSlot.getStart()) &&
                        talk.getEnd().toLocalTime().equals(timeSlot.getEnd()))
                .map(talk -> new AgendaItem(talk.getTitle(),
                        talk.getLocation(),
                        talk.getLocationLink())).collect(Collectors.toList());
    }

    private Map<String, AgendaTalk> findTrackTalksByTimeSlot(@Nonnull LocalDate localDate, @Nonnull TimeSlot timeSlot) {

        Set<String> tracks = getTalks().stream()
                .filter(talk -> talk.getStart()!=null && talk.getEnd() != null && talk.getTrack() != null)
                .map(Talk::getTrack)
                .collect(Collectors.toSet());
        Map<String, AgendaTalk> result = new HashMap<>();
        for (String track : tracks) {

            Optional<AgendaTalk> agendaTalk = getTalks().stream()
                    .filter(talk -> talk.getStart()!=null &&
                            talk.getEnd() != null &&
                            talk.getTrack()!=null &&
                            talk.getTrack().equals(track) &&
                            talk.getStart().toLocalDate().equals(localDate) &&
                            talk.getStart().toLocalTime().equals(timeSlot.getStart()) &&
                            talk.getEnd().toLocalTime().equals(timeSlot.getEnd()))
                    .map(talk -> {
                        List<AgendaTalkSpeaker> speakers = talk.getSpeakers() == null ? new ArrayList<>() : talk.getSpeakers()
                                .stream()
                                .map(speakerUid -> {
                                    Speaker s = speakerRepository.findSpeakerByUid(speakerUid);
                                    if (s == null) {
                                        return null;
                                    }
                                    return s;
                                })
                                .filter(Objects::nonNull)
                                .map(speaker -> new AgendaTalkSpeaker(speaker.getUid(),
                                        speaker.getName()))
                                .collect(Collectors.toList());
                        return new AgendaTalk(talk.getUid(),
                                talk.getTitle(),
                                speakers,
                                talk.getLocation(),
                                talk.getLocationLink());
                    }).findFirst();
            if (agendaTalk.isPresent()) {
                result.put(track, agendaTalk.get());
            }
        }
        return result;
    }

    private Set<LocalDate> getDays() {
        return getTalks().stream()
                .map(Talk::getStart)
                .filter(Objects::nonNull)
                .map(s -> LocalDate.of(s.getYear(), s.getMonth(), s.getDayOfMonth()))
                .collect(Collectors.toSet());
    }


    private Set<TimeSlot> findTimesByDate(LocalDate day) {
        return getTalks().stream()
                .filter(talk -> talk.getStart() != null &&
                        talk.getEnd() != null &&
                        talk.getStart().toLocalDate().equals(day))
                .map(talk -> new TimeSlot(talk.getStart().toLocalTime(), talk.getEnd().toLocalTime()))
                .collect(Collectors.toSet());
    }
}
