package greachconf.controllers;

import greachconf.repositories.TalkRepository;
import greachconf.vm.Talk;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.validation.Validated;
import io.micronaut.views.View;

import javax.validation.constraints.NotBlank;

@Validated
@Controller("/talk")
public class TalkController {

    private final TalkRepository talkRepository;

    public TalkController(TalkRepository talkRepository) {
        this.talkRepository = talkRepository;
    }

    @View("talk")
    @Get("/{uid}")
    public Talk show(@NotBlank String uid) {
        return talkRepository.findTalkByUid(uid);
    }
}
