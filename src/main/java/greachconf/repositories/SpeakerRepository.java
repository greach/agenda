package greachconf.repositories;

import greachconf.vm.Speaker;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import java.util.List;

public interface SpeakerRepository {

    @Nullable
    Speaker findSpeakerByUid(@Nonnull @NotBlank String uid);

    @Nonnull
    List<Speaker> findAllSpeakers();
}
