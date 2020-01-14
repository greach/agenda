package greachconf.mockbean

import com.greachconf.SpeakerPage
import greachconf.repositories.AgendaRepository
import greachconf.vm.Agenda
import greachconf.vm.AgendaDay
import greachconf.vm.AgendaTalk
import greachconf.vm.AgendaTalkSpeaker
import greachconf.vm.AgendaTimeSlot
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

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
        String agendaJson = client.retrieve(HttpRequest.GET("/api/agenda"), String)

        then:
        agendaJson

        cleanup:
        save('build/dist/api', 'agenda.json', agendaJson)
        AgendaRepository agendaRepository = embeddedServer.applicationContext.getBean(AgendaRepository)
        Agenda agenda = agendaRepository.findAgenda()
            for (AgendaDay agendaDay : agenda.days) {
                for (AgendaTimeSlot agendaTimeSlot : agendaDay.timeSlots) {
                    for (AgendaTalk talk : agendaTimeSlot.trackTalks.values()) {
                        String talkJson = client.retrieve(HttpRequest.GET("/api/talk/${talk.uid}"), String)
                        save('build/dist/api/talk/', talk.uid +'.json', talkJson)
                        for (AgendaTalkSpeaker agendaTalkSpeaker : talk.getSpeakers()) {
                            String speakerJson = client.retrieve(HttpRequest.GET("/api/speaker/$agendaTalkSpeaker.uid"), String)
                            save('build/dist/api/speaker/', agendaTalkSpeaker.uid +'.json', speakerJson)
                        }
                    }
                }
            }
        }

    private void save(String f, String file, String json) {
        File folder = new File(f)
        folder.mkdirs()
        File jsonFile = new File(folder, "/"+ file)
        jsonFile.createNewFile()
        jsonFile.text = json
    }
}
