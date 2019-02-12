package greachconf.repositories;

import greachconf.configuration.AgendaConfiguration;
import greachconf.vm.Talk;
import io.micronaut.validation.Validated;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Singleton;
import javax.validation.constraints.NotBlank;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Validated
@Singleton
public class DefaultTalkRepository implements TalkRepository {

    private final AgendaConfiguration agendaConfiguration;

    public DefaultTalkRepository(AgendaConfiguration agendaConfiguration) {
        this.agendaConfiguration = agendaConfiguration;
    }

    @Nullable
    @Override
    public Talk findTalkByUid(@Nonnull @NotBlank String uid) {
        Optional<Talk> talk = agendaConfiguration.getTalks().stream()
                .filter(t -> t.getUid().equals(uid))
                .findFirst();
        return talk.orElse(null);
    }

    @Nonnull
    @Override
    public Set<Talk> findAllBySpeakerUid(@Nonnull @NotBlank String uid) {
        return agendaConfiguration.getTalks().stream()
                .filter(t -> t.getSpeakers().contains(uid))
                .collect(Collectors.toSet());
    }

}
