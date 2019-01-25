package greachconf.controllers;

import greachconf.repositories.SpeakerRepository;
import greachconf.vm.Speaker;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.validation.Validated;
import io.micronaut.views.View;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@Controller("/speaker")
public class SpeakerShowController {

    private final SpeakerRepository speakerRepository;

    public SpeakerShowController(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    @View("speaker")
    @Get("/{uid}")
    public Speaker show(@NotBlank String uid) {
        return speakerRepository.findSpeakerByUid(uid);
    }
}
