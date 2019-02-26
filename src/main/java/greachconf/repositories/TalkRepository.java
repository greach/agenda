package greachconf.repositories;

import greachconf.vm.Talk;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TalkRepository {

    @Nullable
    Talk findTalkByUid(@Nonnull @NotBlank String uid);

    @Nonnull
    Set<Talk> findAllBySpeakerUid(@NotBlank @Nonnull String uid);

    @Nonnull
    Set<Talk> findTalksByTag(@Nonnull @NotBlank String tag);

    @Nonnull
    Map<String, Integer> calculateTagDensity();
}
