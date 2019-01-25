package greachconf.repositories;

import greachconf.vm.Agenda;

import javax.annotation.Nonnull;

public interface AgendaRepository {

    @Nonnull
    Agenda findAgenda();
}
