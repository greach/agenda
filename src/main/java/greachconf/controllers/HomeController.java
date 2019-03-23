package greachconf.controllers;

import greachconf.vm.Agenda;
import greachconf.repositories.AgendaRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

@Controller("/")
public class HomeController {

    private final AgendaRepository agendaRepository;

    public HomeController(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @View("home")
    @Get
    public Agenda index() {
        return agendaRepository.findAgenda();
    }

}
