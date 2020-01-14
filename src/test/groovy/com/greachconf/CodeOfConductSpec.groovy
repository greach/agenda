package com.greachconf

class CodeOfConductSpec extends GebSpecification {

    void "CFP link is not present at Code of Conduct Page"() {
        when:
        CodeOfConductPage page = browser.to CodeOfConductPage

        then:
        !page.mainNav.existsMenu('CFP')
    }

    void "Diversity link is not present at Code of Conduct Page"() {
        when:
        CodeOfConductPage page = browser.to CodeOfConductPage

        then:
        !page.mainNav.existsMenu('Diversity')
    }

    void "links are present at Code of Conduct Page"() {
        when:
        CodeOfConductPage page = browser.to CodeOfConductPage

        then:
        page.mainNav.existsMenu('Speakers')
        page.mainNav.existsMenu('Agenda')
        page.mainNav.existsMenu('Venue')
        page.mainNav.existsMenu('Tickets')
        page.mainNav.existsMenu('FAQ')
        page.mainNav.existsMenu('Sponsors')
        page.mainNav.existsMenu('COC')
    }
}
