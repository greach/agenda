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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Validated
@Controller("/talk")
public class TalkController {

    private final TalkRepository talkRepository;
    private final SpeakerRepository speakerRepository;

    public TalkController(TalkRepository talkRepository,
                          SpeakerRepository speakerRepository) {
        this.talkRepository = talkRepository;
        this.speakerRepository = speakerRepository;
    }

    @View("talk")
    @Get("/{uid}")
    public Map<String, Object> show(@NotBlank String uid) {
        Map<String, Object> model = new HashMap<>();
        Talk talk = talkRepository.findTalkByUid(uid);
        model.put("talk", talk);
        List<Speaker> speakers = new ArrayList<>();
        if (talk.getSpeakers() != null) {
            speakers = talk.getSpeakers()
                    .stream()
                    .map(speakerRepository::findSpeakerByUid)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        model.put("speakers", speakers);
        return model;
    }
}
