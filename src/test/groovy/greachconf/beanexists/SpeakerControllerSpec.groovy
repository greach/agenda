package greachconf.beanexists

import greachconf.controllers.SpeakerController
import io.micronaut.context.ApplicationContext
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class SpeakerControllerSpec extends Specification {

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = ApplicationContext.run()

    def "SpeakerController bean exists"() {
        expect:
        applicationContext.containsBean(SpeakerController)
    }
}
