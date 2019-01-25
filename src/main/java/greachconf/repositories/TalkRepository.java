package greachconf.repositories;

import greachconf.vm.Talk;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;

public interface TalkRepository {

    @Nullable
    Talk findTalkByUid(@Nonnull @NotBlank String uid);
}
