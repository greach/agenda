package greachconf.rest

import greachconf.vm.Speaker
import greachconf.vm.Talk
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
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
    HttpClient client = HttpClient.create(embeddedServer.URL)

    def "can fetch as speaker as JSON at /api/speaker"() {
        given:
        String uri = speakerUri('katia-aresti')
        HttpRequest request = HttpRequest.GET(uri)

        when:
        HttpResponse<Speaker> response = client.toBlocking().exchange(request, Speaker)

        then:
        response.status() == HttpStatus.OK
        response.body()
        response.body().name == 'Katia Aresti'
    }

    def "fetching a non existing speaker returns 404"() {
        given:
        String uri = speakerUri('bogus')
        HttpRequest request = HttpRequest.GET(uri)

        when:
        client.toBlocking().exchange(request, Speaker)

        then:
        HttpClientResponseException e = thrown()
        e.response.status() == HttpStatus.NOT_FOUND
    }

    def "can fetch as talk as JSON at /api/talk"() {
        given:
        String uri = talkUri('micronaut-workshop')
        HttpRequest request = HttpRequest.GET(uri)

        when:
        HttpResponse<Talk> response = client.toBlocking().exchange(request, Talk)

        then:
        response.status() == HttpStatus.OK
        response.body()
        response.body().title == 'Micronaut Workshop'
    }

    def "fetching a non existing talk returns 404"() {
        given:
        String uri = talkUri('bogus')
        HttpRequest request = HttpRequest.GET(uri)

        when:
        client.toBlocking().exchange(request, Talk)

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
