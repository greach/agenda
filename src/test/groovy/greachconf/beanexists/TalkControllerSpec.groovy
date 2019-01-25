package greachconf.beanexists

import greachconf.controllers.TalkController
import io.micronaut.context.ApplicationContext
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class TalkControllerSpec extends Specification {

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = ApplicationContext.run()

    def "TalkController bean exists"() {
        expect:
        applicationContext.containsBean(TalkController)
    }
}
