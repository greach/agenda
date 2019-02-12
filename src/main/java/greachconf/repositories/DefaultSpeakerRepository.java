package greachconf.repositories;

import greachconf.configuration.AgendaConfiguration;
import greachconf.vm.Speaker;
import io.micronaut.validation.Validated;

import javax.annotation.Nullable;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Validated
@Singleton
public class DefaultSpeakerRepository implements SpeakerRepository {

    private final AgendaConfiguration agendaConfiguration;

    public DefaultSpeakerRepository(AgendaConfiguration agendaConfiguration) {
        this.agendaConfiguration = agendaConfiguration;
    }

    @Nullable
    @Override
    public Speaker findSpeakerByUid(String uid) {
        Optional<Speaker> speaker = agendaConfiguration.getSpeakers().stream()
                .filter(s -> s.getUid().equals(uid))
                .findFirst();
        return speaker.orElse(null);
    }

    @Override
    public List<Speaker> findAllSpeakers() {
        return agendaConfiguration.getSpeakers();
    }
}
