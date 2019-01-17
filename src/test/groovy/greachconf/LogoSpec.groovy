package greachconf

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class LogoSpec extends Specification {

    @AutoCleanup
    @Shared
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    @AutoCleanup
    @Shared
    HttpClient client = HttpClient.create(embeddedServer.URL)

    def "logo.png is exposed"() {
        when:
        HttpResponse response = client.toBlocking().exchange(HttpRequest.GET("/images/logo.png"))

        then:
        response.status() == HttpStatus.OK
    }
}
