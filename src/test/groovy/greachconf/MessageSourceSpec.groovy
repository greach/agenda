package greachconf

import io.micronaut.context.ApplicationContext
import io.micronaut.i18n.LocaleResolver
import io.micronaut.i18n.MessageSource
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class MessageSourceSpec extends Specification {

    @Shared
    @AutoCleanup
    ApplicationContext applicationContext = ApplicationContext.run()

    def "MessageSource bean exists"() {
        when:
        MessageSource messageSource = applicationContext.getBean(MessageSource)

        then:
        noExceptionThrown()

        when:
        LocaleResolver localeResolver = applicationContext.getBean(LocaleResolver)

        then:
        noExceptionThrown()

        when:
        String message = messageSource.getMessage('pageTitle', null, "Title", localeResolver.resolveLocale(null))

        then:
        message
        message.contains 'Agenda | Greach 2'
    }
}
