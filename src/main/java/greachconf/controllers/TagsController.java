package greachconf.controllers;

import greachconf.repositories.SpeakerRepository;
import greachconf.repositories.TalkRepository;
import greachconf.vm.Talk;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller("/talks")
public class TagsController {

    private final TalkRepository talkRepository;

    public TagsController(TalkRepository talkRepository) {
        this.talkRepository = talkRepository;
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
        model.put("talks", talks);
        return model;
    }
}
