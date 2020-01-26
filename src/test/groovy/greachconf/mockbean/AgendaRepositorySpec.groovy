package greachconf.mockbean

import com.fasterxml.jackson.databind.ObjectMapper
import com.greachconf.SpeakerPage
import greachconf.repositories.AgendaRepository
import greachconf.vm.Agenda
import greachconf.vm.AgendaDay
import greachconf.vm.AgendaTalk
import greachconf.vm.AgendaTalkSpeaker
import greachconf.vm.AgendaTimeSlot
import groovy.xml.MarkupBuilder
import groovy.xml.XmlUtil
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import java.text.SimpleDateFormat

class AgendaRepositorySpec extends Specification {

    @AutoCleanup
    @Shared
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, ['spec.name': 'agendaRepositorySpec'])

    @Shared
    @AutoCleanup
    HttpClient httpClient = embeddedServer.applicationContext.createBean(HttpClient, embeddedServer.URL)

    @Shared
    ObjectMapper objectMapper = embeddedServer.applicationContext.getBean(ObjectMapper)

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
        Set<AgendaTalkSpeaker> speakers = new HashSet<>()
        Agenda agenda = agendaRepository.findAgenda()
        for (AgendaDay agendaDay : agenda.days) {
            for (AgendaTimeSlot agendaTimeSlot : agendaDay.timeSlots) {
                for (AgendaTalk talk : agendaTimeSlot.trackTalks.values()) {
                    String talkJson = client.retrieve(HttpRequest.GET("/api/talk/${talk.uid}"), String)
                    save('build/dist/api/talk', talk.uid +'.json', talkJson)
                    for (AgendaTalkSpeaker agendaTalkSpeaker : talk.getSpeakers()) {
                        String speakerJson = client.retrieve(HttpRequest.GET("/api/speaker/$agendaTalkSpeaker.uid"), String)
                        save('build/dist/api/speaker', agendaTalkSpeaker.uid +'.json', speakerJson)
                        speakers.add(agendaTalkSpeaker)
                    }
                }
            }
        }

        String speakersJson = objectMapper.writeValueAsString(speakers)
        save('build/dist/api', 'speakers.json', speakersJson)


        String lastmodified = new SimpleDateFormat("yyyy-MM-dd").format(new Date())
        StringWriter writer = new StringWriter()
        new MarkupBuilder(writer).urlset(xmlns: "http://www.sitemaps.org/schemas/sitemap/0.9") {
            speakers.each { speaker ->
                url {
                    loc("https://greachconf.com/speaker/${speaker.uid}/index.html")
                    lastmod(lastmodified)
                }
            }
            agenda.days.each { agendaDay ->
                agendaDay.timeSlots.each { agendaTimeSlot ->
                    agendaTimeSlot.trackTalks.values().each { talk ->
                        url {
                            loc("https://greachconf.com/talk/${talk.uid}/index.html")
                            lastmod(lastmodified)
                        }
                    }
                }
            }
            url {
                loc("https://greachconf.com/cfp/index.html")
                lastmod(lastmodified)
            }
            url {
                loc("https://greachconf.com/coc/index.html")
                lastmod(lastmodified)
            }
            url {
                loc("https://greachconf.com/faq/index.html")
                lastmod(lastmodified)
            }
            url {
                loc("https://greachconf.com/speakers/index.html")
                lastmod(lastmodified)
            }
            url {
                loc("https://greachconf.com/agenda/index.html")
                lastmod(lastmodified)
            }
            url {
                loc("https://greachconf.com/sponsors/index.html")
                lastmod(lastmodified)
            }
            url {
                loc("https://greachconf.com/tickets/index.html")
                lastmod(lastmodified)
            }
            url {
                loc("https://greachconf.com/venue/index.html")
                lastmod(lastmodified)
            }
            url {
                loc("https://greachconf.com/index.html")
                lastmod(lastmodified)
            }
        }
        save('build/dist', 'sitemap.xml', XmlUtil.serialize(writer.toString()))
    }


    private void save(String f, String file, String json) {
        File folder = new File(f)
        folder.mkdirs()
        File jsonFile = new File(f, "/" + file)
        jsonFile.createNewFile()
        jsonFile.text = json
    }
}
