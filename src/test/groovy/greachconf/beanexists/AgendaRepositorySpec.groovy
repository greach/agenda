package greachconf.beanexists

import greachconf.repositories.TalkRepository
import io.micronaut.context.ApplicationContext
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class AgendaRepositorySpec extends Specification {

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = ApplicationContext.run()

    def "greachconf.repositories.TalkRepository bean exists"() {
        expect:
        applicationContext.containsBean(TalkRepository)
    }
}
