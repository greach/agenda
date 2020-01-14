package greachconf.rest

import greachconf.vm.Speaker
import greachconf.vm.Talk
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.http.uri.UriTemplate
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class ApiControllerSpec extends Specification {

    @AutoCleanup
    @Shared
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    @AutoCleanup
    @Shared
    HttpClient httpClient = HttpClient.create(embeddedServer.URL)

    BlockingHttpClient getClient() {
        httpClient.toBlocking()
    }

    def "fetching a non existing speaker returns 404"() {
        given:
        String uri = speakerUri('bogus')
        HttpRequest request = HttpRequest.GET(uri)

        when:
        client.exchange(request, Speaker)

        then:
        HttpClientResponseException e = thrown()
        e.response.status() == HttpStatus.NOT_FOUND
    }

    def "fetching a non existing talk returns 404"() {
        given:
        String uri = talkUri('bogus')
        HttpRequest request = HttpRequest.GET(uri)

        when:
        client.exchange(request)

        then:
        HttpClientResponseException e = thrown()
        e.response.status() == HttpStatus.NOT_FOUND
    }

    private speakerUri(String uid) {
        UriTemplate uriTemplate = new UriTemplate('/api/speaker/{uid}')
        uriTemplate.expand(Collections.singletonMap("uid", uid))
    }

    private talkUri(String uid) {
        UriTemplate uriTemplate = new UriTemplate('/api/talk/{uid}')
        uriTemplate.expand(Collections.singletonMap("uid", uid))
    }
}
