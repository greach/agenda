package greachconf.staticresources

import spock.lang.Specification

class MainCssSpec extends Specification {

    def "/stylesheets/main.css returns 200"() {
        //when:
        // DO request

        expect: //then:
        // request respond 200
        false
    }
}
