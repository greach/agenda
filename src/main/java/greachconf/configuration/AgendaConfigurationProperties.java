package greachconf.configuration;

import greachconf.vm.Speaker;
import greachconf.vm.Talk;
import io.micronaut.context.annotation.ConfigurationProperties;

import javax.annotation.Nonnull;
import java.util.List;

@ConfigurationProperties("agenda")
public class AgendaConfigurationProperties implements AgendaConfiguration {

    @Nonnull
    private List<Speaker> speakers;

    @Nonnull
    private List<Talk> talks;


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
