package greachconf.beanexists

import greachconf.controllers.AgendaController
import io.micronaut.context.ApplicationContext
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class HomeControllerSpec extends Specification {

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = ApplicationContext.run()

    def "AgendaController bean exists"() {
        expect:
        applicationContext.containsBean(AgendaController)
    }
}
