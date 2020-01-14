package greachconf.controllers;

import greachconf.vm.Agenda;
import greachconf.repositories.AgendaRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

@Controller("/agenda")
public class AgendaController {

    private final AgendaRepository agendaRepository;

    public AgendaController(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @View("agenda")
    @Get("/index.html")
    public Agenda index() {
        return agendaRepository.findAgenda();
    }

}
