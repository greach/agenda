package greachconf.ui

import geb.spock.GebSpec
import greachconf.ui.pages.HomePage
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared

class HomeSpec extends GebSpec {

    @AutoCleanup
    @Shared
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    def "if you visit the home page an HTML page is rendered whose title contains Greach"() {
        given:
        browser.baseUrl = embeddedServer.URL.toString()

        when:
        to HomePage

        then:
        at HomePage
    }
}
