package greachconf.controllers;

import greachconf.repositories.SpeakerRepository;
import greachconf.repositories.TalkRepository;
import greachconf.vm.Speaker;
import greachconf.vm.Talk;
import greachconf.vm.TalkRow;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller("/talks")
public class TagsController {

    private final TalkRepository talkRepository;
    private final SpeakerRepository speakerRepository;

    public TagsController(TalkRepository talkRepository,
                          SpeakerRepository speakerRepository) {
        this.talkRepository = talkRepository;
        this.speakerRepository = speakerRepository;
    }

    @View("tags")
    @Get("/tags")
    public Map<String, Object> index() {
        Map<String, Object> model = new HashMap<>();
        Map<String, Integer> tagDensitiy = talkRepository.calculateTagDensity();
        model.put("tagDensity", tagDensitiy);
        return model;
    }

    @View("tag")
    @Get("/tags/{tag}")
    public Map<String, Object> tag(@NotBlank String tag) {
        Map<String, Object> model = new HashMap<>();
        model.put("tag", tag.toUpperCase());
        Set<Talk> talks = talkRepository.findTalksByTag(tag);
        List<TalkRow> talkRows = new ArrayList<>(talks.size());
        for (Talk talk : talks) {
            Set<String> speakersUid = talk.getSpeakers();
            if (speakersUid != null) {
                Set<String> speakersNames = new HashSet<>(speakersUid.size());
                for (String speakerUid : speakersUid) {
                    Speaker speaker = speakerRepository.findSpeakerByUid(speakerUid);
                    if (speaker != null) {
                        speakersNames.add(speaker.getName());
                    }
                }
                TalkRow talkRow = new TalkRow(talk.getUid(),
                        talk.getTitle(),
                        speakersNames,
                        talk.getLocation(),
                        talk.getLocationLink());
                talkRows.add(talkRow);
            }

        }
        Collections.sort(talkRows);
        model.put("talks", talkRows);
        return model;
    }


}
