package greachconf.configuration;

import greachconf.repositories.AgendaRepository;
import greachconf.repositories.SpeakerRepository;
import greachconf.repositories.TalkRepository;
import greachconf.vm.Agenda;
import greachconf.vm.AgendaDay;
import greachconf.vm.AgendaItem;
import greachconf.vm.AgendaTalk;
import greachconf.vm.AgendaTalkSpeaker;
import greachconf.vm.AgendaTimeSlot;
import greachconf.vm.Speaker;
import greachconf.vm.Talk;
import greachconf.vm.TimeSlot;
import io.micronaut.context.annotation.ConfigurationProperties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ConfigurationProperties("agenda")
public class AgendaRepositoryConfigurationProperties implements AgendaRepository, TalkRepository, SpeakerRepository {

    @Nonnull
    private List<Speaker> speakers;

    @Nonnull
    private List<Talk> talks;

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

    private List<AgendaItem> findUnTrackTalskByTimeSlot(@Nonnull LocalDate localDate, @Nonnull TimeSlot timeSlot) {
        return talks.stream()
                .filter(talk -> talk.getStart()!=null && talk.getEnd() != null && (talk.getTrack() == null || talk.getTrack().isBlank()))
                .filter(talk -> talk.getStart().toLocalDate().equals(localDate) &&
                        talk.getStart().toLocalTime().equals(timeSlot.getStart()) &&
                        talk.getEnd().toLocalTime().equals(timeSlot.getEnd()))
                .map(talk -> new AgendaItem(talk.getTitle())).collect(Collectors.toList());
    }

    private Map<String, AgendaTalk> findTrackTalksByTimeSlot(@Nonnull LocalDate localDate, @Nonnull TimeSlot timeSlot) {

        Set<String> tracks = talks.stream()
                .filter(talk -> talk.getStart()!=null && talk.getEnd() != null && talk.getTrack() != null)
                .map(Talk::getTrack)
                .collect(Collectors.toSet());
        Map<String, AgendaTalk> result = new HashMap<>();
        for (String track : tracks) {

            Optional<AgendaTalk> agendaTalk = talks.stream()
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
                                    Speaker s = findSpeakerByUid(speakerUid);
                                    if (s == null) {
                                        return null;
                                    }
                                    return s;
                                })
                                .filter(Objects::nonNull)
                                .map(speaker -> new AgendaTalkSpeaker(speaker.getUid(),
                                        speaker.getName()))
                                .collect(Collectors.toList());
                        return new AgendaTalk(talk.getUid(), talk.getTitle(), speakers);
                    }).findFirst();
            if (agendaTalk.isPresent()) {
                result.put(track, agendaTalk.get());
            }
        }
        return result;
    }

    private Set<LocalDate> getDays() {
        return talks.stream()
                .map(Talk::getStart)
                .filter(Objects::nonNull)
                .map(s -> LocalDate.of(s.getYear(), s.getMonth(), s.getDayOfMonth()))
                .collect(Collectors.toSet());
    }


    private Set<TimeSlot> findTimesByDate(LocalDate day) {
        return talks.stream()
                .filter(talk -> talk.getStart() != null &&
                        talk.getEnd() != null &&
                        talk.getStart().toLocalDate().equals(day))
                .map(talk -> new TimeSlot(talk.getStart().toLocalTime(), talk.getEnd().toLocalTime()))
                .collect(Collectors.toSet());
    }

    @Nullable
    @Override
    public Talk findTalkByUid(String uid) {
        Optional<Talk> talk = talks.stream()
                .filter(t -> t.getUid().equals(uid))
                .findFirst();
        return talk.orElse(null);
    }

    @Nullable
    @Override
    public Speaker findSpeakerByUid(String uid) {
        Optional<Speaker> speaker = speakers.stream()
                .filter(s -> s.getUid().equals(uid))
                .findFirst();
        return speaker.orElse(null);
    }

    @Override
    public List<Speaker> findAllSpeakers() {
        return getSpeakers();
    }

    @Nonnull
    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(@Nonnull List<Speaker> speakers) {
        this.speakers = speakers;
    }

    @Nonnull
    public List<Talk> getTalks() {
        return talks;
    }



    public void setTalks(@Nonnull List<Talk> talks) {
        this.talks = talks;
    }
}
