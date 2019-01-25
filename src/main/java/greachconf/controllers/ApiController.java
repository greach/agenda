package greachconf.controllers;

import greachconf.repositories.AgendaRepository;
import greachconf.repositories.SpeakerRepository;
import greachconf.repositories.TalkRepository;
import greachconf.vm.Agenda;
import greachconf.vm.Speaker;
import greachconf.vm.Talk;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.validation.constraints.NotBlank;

@Controller("/api")
public class ApiController {

    private final AgendaRepository agendaRepository;
    private final TalkRepository talkRepository;
    private final SpeakerRepository speakerRepository;

    public ApiController(AgendaRepository agendaRepository,
                         TalkRepository talkRepository,
                         SpeakerRepository speakerRepository) {
        this.agendaRepository = agendaRepository;
        this.talkRepository = talkRepository;
        this.speakerRepository = speakerRepository;
    }

    @Get("/agenda")
    public Agenda index() {
        return agendaRepository.findAgenda();
    }

    @Get("/speaker/{uid}")
    public Speaker speaker(@NotBlank String uid) {
        return speakerRepository.findSpeakerByUid(uid);
    }

    @Get("/talk/{uid}")
    public Talk talk(@NotBlank String uid) {
        return talkRepository.findTalkByUid(uid);
    }
}
