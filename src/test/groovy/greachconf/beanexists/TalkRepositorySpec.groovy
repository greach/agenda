package greachconf.beanexists

import greachconf.configuration.AgendaConfiguration
import greachconf.repositories.DefaultTalkRepository
import greachconf.repositories.TalkRepository
import greachconf.vm.Speaker
import greachconf.vm.Talk
import io.micronaut.context.ApplicationContext
import io.micronaut.context.annotation.Primary
import io.micronaut.context.annotation.Requires
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Singleton
import javax.validation.ConstraintViolationException

class TalkRepositorySpec extends Specification {

    @Shared
    Map<String, Object> conf = ["spec.name": "TalkRepositorySpec"]

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = ApplicationContext.run(conf)

    def "TalkRepository bean exists"() {
        expect:
        applicationContext.containsBean(TalkRepository)
    }

    def "findAllBySpeakerUid triggers validation exception"() {
        when:
        TalkRepository talkRepository = applicationContext.getBean(TalkRepository)

        then:
        noExceptionThrown()
        talkRepository instanceof DefaultTalkRepository

        when:
        talkRepository.findAllBySpeakerUid("")

        then:
        thrown(ConstraintViolationException)
    }

    def "findTalkByUid triggers validation exception"() {
        when:
        TalkRepository talkRepository = applicationContext.getBean(TalkRepository)

        then:
        noExceptionThrown()
        talkRepository instanceof DefaultTalkRepository

        when:
        talkRepository.findTalkByUid("")

        then:
        thrown(ConstraintViolationException)
    }

    @Requires(property = "spec.name", value = "TalkRepositorySpec")
    @Primary
    @Singleton
    static class MockAgendaConfiguration implements AgendaConfiguration {

        @Override
        List<Speaker> getSpeakers() {
            []
        }

        @Override
        List<Talk> getTalks() {
            []
        }
    }
}


