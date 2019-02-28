package greachconf.beanexists

import greachconf.views.DateTimeViewModelProcessor
import io.micronaut.context.ApplicationContext
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class DateTimeViewModelProcessorSpec extends Specification {

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = ApplicationContext.run()

    void "DateTimeViewModelProcessor exists"() {
        expect:
        applicationContext.containsBean(DateTimeViewModelProcessor)
    }

}
