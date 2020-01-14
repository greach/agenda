package greachconf.controllers;

import greachconf.repositories.SpeakerRepository;
import greachconf.repositories.TalkRepository;
import greachconf.vm.Speaker;
import greachconf.vm.Talk;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.validation.Validated;
import io.micronaut.views.View;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Validated
@Controller("/speaker")
public class SpeakerShowController {

    private final SpeakerRepository speakerRepository;
    private final TalkRepository talkRepository;

    public SpeakerShowController(SpeakerRepository speakerRepository,
                                 TalkRepository talkRepository) {
        this.speakerRepository = speakerRepository;
        this.talkRepository = talkRepository;
    }

    @View("speaker")
    @Get("/{uid}/index.html")
    public Map<String, Object> show(@NotBlank String uid) {
        Map<String, Object> model = new HashMap<>();
        Speaker speaker = speakerRepository.findSpeakerByUid(uid);
        if (speaker != null) {
            model.put("speaker", speaker);
            Set<Talk> talks = talkRepository.findAllBySpeakerUid(speaker.getUid());
            model.put("talks", talks);
        }
        return model;
    }
}
