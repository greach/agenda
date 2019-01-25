package greachconf.staticresources

import spock.lang.Specification

class LogoSpec extends Specification {

    def "/images/logo.png returns 200"() {
        //when:
        // DO request

        expect: //then:
        // request respond 200
        false
    }
}
