package com.greachconf

import geb.Browser
import geb.spock.GebReportingSpec
import geb.spock.GebSpec
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared

abstract class GebSpecification extends GebReportingSpec implements ConfigurationFixture {

    @AutoCleanup
    @Shared
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, configuration)

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = embeddedServer.applicationContext

    String getBaseUrl() {
        embeddedServer.URL.toString()
    }

    Browser getBrowser() {
        Browser browser = super.getBrowser()
        if (browser.baseUrl == null) {
            browser.baseUrl = baseUrl
        }
        browser
    }
}
