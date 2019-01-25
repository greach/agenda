package greachconf.ui

import geb.spock.GebSpec
import greachconf.controllers.TalkController
import greachconf.ui.pages.TalkPage
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared

class TalkControllerSpec extends GebSpec {

    @AutoCleanup
    @Shared
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)
    
    def "TalkController bean exists"() {
        expect:
        embeddedServer.applicationContext.containsBean(TalkController)
    }

    def "if you visit a talk page an HTML page is rendered whose title contains Micronaut Workshop"() {
        given:
        browser.baseUrl = embeddedServer.URL.toString()

        when:
        to TalkPage, 'micronaut-workshop'

        then:
        at TalkPage

        and:
        browser.page(TalkPage).talkTitle == 'Micronaut Workshop'
    }


}