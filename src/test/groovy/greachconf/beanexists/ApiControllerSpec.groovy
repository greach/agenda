package greachconf.beanexists

import greachconf.controllers.ApiController
import io.micronaut.context.ApplicationContext
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class ApiControllerSpec extends Specification {

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = ApplicationContext.run()

    def "ApiController bean exists"() {
        expect:
        applicationContext.containsBean(ApiController)
    }
}
