package greachconf.configuration;

import greachconf.vm.Speaker;
import greachconf.vm.Talk;

import javax.annotation.Nonnull;
import java.util.List;

public interface AgendaConfiguration {

    @Nonnull
    List<Speaker> getSpeakers();

    @Nonnull
    List<Talk> getTalks();
}
