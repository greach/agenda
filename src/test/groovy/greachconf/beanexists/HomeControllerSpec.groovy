package greachconf.beanexists

import greachconf.controllers.HomeController
import io.micronaut.context.ApplicationContext
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class HomeControllerSpec extends Specification {

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = ApplicationContext.run()

    def "HomeController bean exists"() {
        expect:
        applicationContext.containsBean(HomeController)
    }
}
