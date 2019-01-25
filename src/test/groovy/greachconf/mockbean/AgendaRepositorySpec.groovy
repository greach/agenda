package greachconf.mockbean

import greachconf.vm.Agenda
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDate

class AgendaRepositorySpec extends Specification {

    @AutoCleanup
    @Shared
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, ['spec.name': 'agendaRepositorySpec'])

    @Shared
    @AutoCleanup
    HttpClient httpClient = embeddedServer.applicationContext.createBean(HttpClient, embeddedServer.URL)

    BlockingHttpClient getClient() {
        httpClient.toBlocking()
    }


    def "Agenda bean exists"() {
        when:
        Agenda agenda = client.retrieve(HttpRequest.GET("/api/agenda"), Agenda)

        then:
        agenda
        agenda.days.size() == 2
        agenda.days.find { it.day == LocalDate.of(2019, 3, 29) }
        agenda.days.find { it.day == LocalDate.of(2019, 3, 28) }
    }
}
