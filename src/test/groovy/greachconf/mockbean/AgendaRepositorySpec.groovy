package greachconf.mockbean


import greachconf.repositories.AgendaRepository
import greachconf.vm.Agenda
import io.micronaut.context.ApplicationContext
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalTime

class AgendaRepositorySpec extends Specification {

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = ApplicationContext.run()

    def "Agenda bean exists"() {
        when:
        AgendaRepository agendaRepository = applicationContext.getBean(AgendaRepository)

        then:
        noExceptionThrown()

        and:
        Agenda agenda = agendaRepository.findAgenda()

        and:
        agenda
        agenda.days.size() == 2
        agenda.days.find { it.day == LocalDate.of(2019, 3, 29) }
        agenda.days.find { it.day == LocalDate.of(2019, 3, 29) }.tracks.size() == 1
    }
}
