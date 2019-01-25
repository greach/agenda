package greachconf.controllers;

import greachconf.repositories.SpeakerRepository;
import greachconf.vm.Speaker;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.validation.Validated;
import io.micronaut.views.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@Controller("/speakers")
public class SpeakerController {

    private final SpeakerRepository speakerRepository;

    public SpeakerController(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    @Get
    @View("speakers")
    public Map<String, Object> index() {
        Map<String, Object> model = new HashMap<>();
        model.put("speakers", speakerRepository.findAllSpeakers());
        return model;
    }

}
