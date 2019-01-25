package greachconf.ui

import geb.spock.GebSpec
import greachconf.controllers.SpeakerController
import greachconf.ui.pages.SpeakerPage
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared

class SpeakerControllerSpec extends GebSpec {

    @AutoCleanup
    @Shared
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    def "SpeakerController bean exists"() {
        expect:
        embeddedServer.applicationContext.containsBean(SpeakerController)
    }

    def "if you visit the home page an HTML page is rendered whose title contains Greach"() {
        given:
        browser.baseUrl = embeddedServer.URL.toString()

        when:
        to SpeakerPage, 'katia-aresti'

        then:
        at SpeakerPage

        and:
        browser.page(SpeakerPage).name == 'Katia Aresti'
    }


}